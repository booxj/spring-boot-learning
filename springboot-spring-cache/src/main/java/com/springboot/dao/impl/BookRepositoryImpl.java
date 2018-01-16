package com.springboot.dao.impl;

import com.springboot.AppRunner;
import com.springboot.dao.BookRepository;
import com.springboot.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 14:03
 * @see:
 * @since:
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

    @Override
    @Cacheable(value = "books",key = "#isbn")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    @Override
    @CacheEvict(value = "books", key = "#isbn")// 清空book缓存
    public void update(String isbn) {

    }

    @Override
    @CacheEvict(value = "books", key = "#book.isbn")// 清空book缓存
    public void update(Book book) {

    }

    @Override
    @CacheEvict(value = "books", allEntries = true)// 清空所有缓存
    public void reload() {}

    private void simulateSlowService() {
        try {
            logger.info("--> sleeping....");
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
