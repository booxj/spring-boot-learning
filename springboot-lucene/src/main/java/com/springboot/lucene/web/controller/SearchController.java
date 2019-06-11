package com.springboot.lucene.web.controller;

import com.springboot.core.domain.Response;
import com.springboot.lucene.daomain.IndexObject;
import com.springboot.lucene.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/11 15:53
 * @since
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private LuceneService luceneService;

    @GetMapping("{keyword}")
    public Response page(@PathVariable String keyword,
                         @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return luceneService.page(pageNumber, pageSize, keyword);
    }

    @PostMapping("create")
    public Response create(@RequestBody IndexObject indexObject) {
        luceneService.create(indexObject);
        return Response.ok("create index success!");
    }

    @DeleteMapping("delete")
    public Response delete(long id) {
        luceneService.delete(id);
        return Response.ok("delete index success!");
    }

    @DeleteMapping("deleteAll")
    public Response deleteAll(){
        luceneService.deleteAll();
        return Response.ok();
    }

    @PutMapping("update")
    public Response update(@RequestBody IndexObject indexObject){
        luceneService.update(indexObject);
        return Response.ok();
    }
}
