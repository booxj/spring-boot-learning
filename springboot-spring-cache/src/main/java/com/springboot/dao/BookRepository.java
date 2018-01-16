package com.springboot.dao;

import com.springboot.model.Book;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 14:03
 * @see:
 * @since:
 */
public interface BookRepository {

    Book getByIsbn(String isbn);

    void update(String isbn);

    void update(Book book);

    public void reload();
}
