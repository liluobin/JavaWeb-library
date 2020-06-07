package com.luobin.repository.impl;

import com.luobin.repository.BorrowRepository;
import com.luobin.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowRepositoryImpl implements BorrowRepository {

    @Override
    public void insert(Integer bookid, Integer readerId, String borrowTime, String returnTime, Integer adminId, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,0)";
        PreparedStatement statement=null;
        try {
            statement =connection.prepareStatement(sql);
            statement.setInt(1,bookid);
            statement.setInt(2,readerId);
            statement.setString(3,borrowTime);
            statement.setString(4,returnTime);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        finally {
            JDBCTools.release(connection,statement,null);
        }

    }
}
