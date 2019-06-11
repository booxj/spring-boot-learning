package com.springboot.lucene.service;

import com.springboot.lucene.daomain.IndexObject;
import com.springboot.lucene.daomain.LuceneResponse;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/11 15:00
 * @since
 */
@Service
public class LuceneService {

    private final static Logger log = LoggerFactory.getLogger(LuceneService.class);

    private Directory directory = null;
    private Analyzer analyzer = null;

    @Value("${lucene.document.path:./lucene/file}")
    private String indexDer;

    @PostConstruct
    public void init() {
        try {
            directory = FSDirectory.open(Paths.get(indexDer));
            analyzer = new SmartChineseAnalyzer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     *
     * @param indexObject
     */
    public void create(IndexObject indexObject) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
            indexWriter = new IndexWriter(directory, config);

            indexWriter.addDocument(IndexObject2Document(indexObject));
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 删除索引
     *
     * @param id
     */
    public void delete(Long id) {
        IndexWriter indexWriter = null;
        try {
            Term term = new Term("id", id.toString());
            IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
            indexWriter = new IndexWriter(directory, config);

            indexWriter.deleteDocuments(term);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 删除索引
     */
    public void deleteAll() {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
            indexWriter = new IndexWriter(directory, config);

            Long result = indexWriter.deleteAll();
            // 清空回收站
            indexWriter.forceMergeDeletes();
            log.info("deleted:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 更新单条索引
     */
    public void update(IndexObject indexObject) {

        IndexWriter indexWriter = null;

        try {
            Term term = new Term("id", indexObject.getId().toString());
            IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
            indexWriter = new IndexWriter(directory, config);

            indexWriter.updateDocument(term, IndexObject2Document(indexObject));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public LuceneResponse page(Integer pageNumber, Integer pageSize, String keyword) {

        IndexReader indexReader = null;
        LuceneResponse luceneResponse = null;
        List<IndexObject> searchResults = new ArrayList<>();
        try {
            indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            Query query = query(keyword, analyzer, "title", "description");
            ScoreDoc lastScoreDoc = this.getLastScoreDoc(pageNumber, pageSize, query, indexSearcher);
            // 将上一页的最后一个document传递给searchAfter方法以得到下一页的结果
            TopDocs topDocs = indexSearcher.searchAfter(lastScoreDoc, query, pageSize);
            Highlighter highlighter = this.addStringHighlighter(query);
            log.info("搜索词语：{}", keyword);
            log.info("总共的查询结果：{}", topDocs.totalHits);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                int docID = scoreDoc.doc;
                float score = scoreDoc.score;
                Document document = indexSearcher.doc(docID);
                IndexObject indexObject = document2IndexObject(analyzer, highlighter, document, score);
                searchResults.add(indexObject);
                log.info("相关度得分：" + score);
            }
            Collections.sort(searchResults);
            luceneResponse = LuceneResponse.ok(searchResults, topDocs.totalHits);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                indexReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return luceneResponse;
    }

    /**
     * 根据页码和分页大小获取上一次的最后一个ScoreDoc
     *
     * @param pageNumber
     * @param pageSize
     * @param query
     * @param searcher
     * @return
     * @throws IOException
     */
    private ScoreDoc getLastScoreDoc(Integer pageNumber, Integer pageSize, Query query, IndexSearcher searcher) throws IOException {
        if (pageNumber == 1) {
            return null;
        }
        int total = pageSize * (pageNumber - 1);
        TopDocs topDocs = searcher.search(query, total);
        return topDocs.scoreDocs[total - 1];
    }

    /**
     * 设置字符串高亮
     *
     * @param query
     * @return
     */
    private Highlighter addStringHighlighter(Query query) {
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        return highlighter;
    }

    /**
     * 关键字加亮
     *
     * @param analyzer
     * @param highlighter
     * @param document
     * @param field
     * @return
     * @throws Exception
     */
    private String stringFormatHighlighterOut(Analyzer analyzer, Highlighter highlighter, Document document, String field) throws Exception {
        String fieldValue = document.get(field);
        if (fieldValue != null) {
            TokenStream tokenStream = analyzer.tokenStream(field, new StringReader(fieldValue));
            return highlighter.getBestFragment(tokenStream, fieldValue);
        }
        return null;
    }

    private Query query(String query, Analyzer analyzer, String... fields) throws ParseException {
        BooleanQuery.setMaxClauseCount(32768);
        // 过滤非法字符
        query.replace("/", " ").replace("\\", " ");
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        parser.setDefaultOperator(QueryParser.Operator.OR);
        return parser.parse(query);
    }


    /**
     * <p>
     * new StringField 不分词(id,身份证号,电话...)
     * new StoredField 不分词(链接)
     * new TextField 分词(文本)
     * 其他
     * </p>
     *
     * @param indexObject
     * @return
     */
    public static Document IndexObject2Document(IndexObject indexObject) {
        Document doc = new Document();
        doc.add(new StringField("id", indexObject.getId().toString(),Field.Store.YES));
        doc.add(new TextField("title", indexObject.getTitle(), Field.Store.YES));
        doc.add(new TextField("summary", indexObject.getKeywords(), Field.Store.YES));
        doc.add(new TextField("description", indexObject.getDescription(), Field.Store.YES));
        doc.add(new StoredField("createDate", indexObject.getCreateDate()));
        doc.add(new StoredField("url", indexObject.getUrl()));
        return doc;
    }

    private IndexObject document2IndexObject(Analyzer analyzer, Highlighter highlighter, Document doc, float score) throws Exception {
        IndexObject indexObject = new IndexObject();
        indexObject.setId(Long.parseLong(doc.get("id")));
        indexObject.setTitle(stringFormatHighlighterOut(analyzer, highlighter, doc, "title"));
        indexObject.setKeywords(stringFormatHighlighterOut(analyzer, highlighter, doc, "summary"));
        indexObject.setDescription(stringFormatHighlighterOut(analyzer, highlighter, doc, "description"));
        indexObject.setCreateDate(doc.get("createDate"));
        indexObject.setUrl(doc.get("url"));
        indexObject.setScore(score);
        return indexObject;
    }
}
