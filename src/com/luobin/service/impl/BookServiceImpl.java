package com.luobin.service.impl;

import com.luobin.entity.Book;
import com.luobin.repository.BookRepository;
import com.luobin.repository.impl.BookRepositoryImpl;
import com.luobin.service.BookService;

import java.util.List;

/**
 * 与业务逻辑无关的处理都交给service
 * 具体的数据库调用交给repository
 */
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository=new BookRepositoryImpl();
    private final int  LIMIT=6;
    @Override
    public List<Book> findAll(int page) {
        int index=(page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count=bookRepository.getPages();
        int pages=(count+LIMIT-1)/LIMIT;
        return pages;
    }
}
