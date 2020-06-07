package com.luobin.service;

import com.luobin.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
}
