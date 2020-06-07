package com.luobin.repository;

import com.luobin.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username, String password);
}
