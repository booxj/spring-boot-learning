package com.springboot.lucene.daomain;

import java.io.Serializable;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/11 15:36
 * @since
 */
public class IndexObject implements Comparable<IndexObject>, Serializable {

    private Long id;

    private String title;

    private String keywords;

    private String description;

    private String createDate;

    private String url;

    // 相似度
    private float score;


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


    public IndexObject() {
        super();
    }

    public IndexObject(Long id, String keywords, String description, String createDate, float score) {
        super();
        this.id = id;
        this.keywords = keywords;
        this.score = score;
        this.description = description;
        this.createDate = createDate;
    }

    @Override
    public int compareTo(IndexObject o) {
        if (this.score < o.getScore()) {
            return 1;
        } else if (this.score > o.getScore()) {
            return -1;
        }
        return 0;
    }
}
