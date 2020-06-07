package com.luobin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/myemployees?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
            String user="root";
            String password="root";
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
