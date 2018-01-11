package com.springboot.dao.impl;

import com.springboot.AppRunner;
import com.springboot.dao.BookRepository;
import com.springboot.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }


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
