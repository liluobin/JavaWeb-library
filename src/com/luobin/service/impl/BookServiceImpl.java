package com.luobin.service.impl;

import com.luobin.entity.Book;
import com.luobin.repository.BookRepository;
import com.luobin.repository.BorrowRepository;
import com.luobin.repository.impl.BookRepositoryImpl;
import com.luobin.repository.impl.BorrowRepositoryImpl;
import com.luobin.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 与业务逻辑无关的处理都交给service
 * 具体的数据库调用交给repository
 */
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository=new BookRepositoryImpl();
    private BorrowRepository borrowRepository=new BorrowRepositoryImpl();
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

    @Override
    public void addBorrow(Integer bookId, Integer readerId) {
        //借书时间
        Date date= new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime =simpleDateFormat.format(date);
        //还书时间，借书时间+14天
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookId,readerId,borrowTime,returnTime,null,0);
    }
}
