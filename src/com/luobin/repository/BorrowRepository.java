package com.luobin.repository;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerId,String borrowTime,String returnTime,Integer adminId, Integer state);
}
