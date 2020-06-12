package com.luobin.service;

import com.luobin.entity.Book;
import com.luobin.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public void addBorrow(Integer bookId, Integer readerId);
    public List<Borrow> findAllByReaderId(Integer readerid,Integer page);
    public int getBorrowPages(Integer readerid);
    public List<Borrow> findAllByState(Integer state ,Integer page);
    public int getBorrowPagesByState(Integer state);
    public void setBorrowById(Integer id,Integer state, Integer adminId);
}
