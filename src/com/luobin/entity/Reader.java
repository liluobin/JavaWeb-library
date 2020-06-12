package com.luobin.entity;

public class Reader {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardid;
    private String gender;

    public Reader(Integer id, String username, String password, String name, String tel, String cardid, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardid = cardid;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getCardid() {
        return cardid;
    }

    public String getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Reader(String name, String tel, String cardid) {
        this.name = name;
        this.tel = tel;
        this.cardid = cardid;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", cardid='" + cardid + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
