package com.luobin.repository.impl;

import com.luobin.entity.Reader;
import com.luobin.repository.ReaderRepository;
import com.luobin.utils.JDBCTools;

import java.sql.*;

public class ReaderRepositoryImpl implements ReaderRepository {


    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement statement= null;
        String sql ="select * from reader where username= ? and password = ? ";
        ResultSet resultSet =null;
        Reader reader =null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet =statement.executeQuery();
            if(resultSet.next()){
                reader=new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return reader;
    }
}
