package com.luobin.repository.impl;

import com.luobin.entity.Book;
import com.luobin.entity.Borrow;
import com.luobin.entity.Reader;
import com.luobin.repository.BorrowRepository;
import com.luobin.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Borrow> findAllByReaderId(Integer readerid,Integer index, Integer limit) {
        Connection connection =JDBCTools.getConnection();
        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state from reader r,book b,borrow br where readerid=? and b.id=br.bookid and r.id=br.readerid limit ? , ?";
        PreparedStatement preparedStatement =null;
        ResultSet resultSet=null;
        List<Borrow> list=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerid);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,limit);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){


                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),resultSet.getString(6),resultSet.getInt(10)
                        ));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int getPages(Integer readerid) {
        Connection connection =JDBCTools.getConnection();
        String sql="select count(*) from reader r,book b,borrow br where readerid=? and b.id=br.bookid and r.id=br.readerid";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Integer result=0;

        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,readerid);
            resultSet =statement.executeQuery();
            if(resultSet.next()){
                result=resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            JDBCTools.release(connection,statement,resultSet);
        }
        return result;
    }

    @Override
    public List<Borrow> findAllByState(Integer state, Integer index, Integer limit) {
        Connection connection =JDBCTools.getConnection();
        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state from reader r,book b,borrow br where state=? and b.id=br.bookid and r.id=br.readerid limit ? , ?";
        PreparedStatement preparedStatement =null;
        ResultSet resultSet=null;
        List<Borrow> list=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,limit);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){


                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),resultSet.getString(6),resultSet.getInt(10)
                ));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int getPagesByState(Integer state) {
        Connection connection =JDBCTools.getConnection();
        String sql="select count(*) from reader r,book b,borrow br where state= ? and b.id=br.bookid and r.id=br.readerid";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Integer result=0;

        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,state);
            resultSet =statement.executeQuery();
            if(resultSet.next()){
                result=resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            JDBCTools.release(connection,statement,resultSet);
        }
        return result;
    }

    @Override
    public void setBorrowById(Integer id, Integer state,Integer adminId) {
        Connection connection =JDBCTools.getConnection();
        String sql="update borrow set state=? , adminid =? where id=? ";
        PreparedStatement statement=null;


        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,adminId);
            statement.setInt(3,id);
            statement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            JDBCTools.release(connection,statement,null);
        }

    }
}
