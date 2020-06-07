package com.luobin.entity;

public class BookCase {
    Integer id;
    String name;

    public BookCase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookCase() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
