package com.luobin.repository;

import com.luobin.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerId,String borrowTime,String returnTime,Integer adminId, Integer state);
    public List<Borrow> findAllByReaderId(Integer readerid,Integer index,Integer limit);
    public int getPages(Integer readerid);
    public List<Borrow> findAllByState(Integer state,Integer index,Integer limit);
    public int getPagesByState(Integer state);
    public void setBorrowById(Integer id, Integer state,Integer adminId);
}
