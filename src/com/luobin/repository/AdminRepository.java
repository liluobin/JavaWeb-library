package com.luobin.repository;

import com.luobin.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
