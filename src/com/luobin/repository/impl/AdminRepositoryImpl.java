package com.luobin.repository.impl;

import com.luobin.entity.Admin;
import com.luobin.repository.AdminRepository;
import com.luobin.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement statement= null;
        String sql ="select * from bookadmin where username= ? and password = ? ";
        ResultSet resultSet =null;
        Admin admin =null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet =statement.executeQuery();
            if(resultSet.next()){
                admin=new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }
}
