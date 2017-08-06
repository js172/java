package com.js.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.js.book.Book;
import com.js.jdbc.JDBCTools;

public class BookDao extends DaoImpl<Book>{

	@Override
	public List<Book> getList(String sql, Object... args) {
		List<Book> bookList = new ArrayList<>();
		Book book = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				book = new Book();				
				book.setBookName(resultSet.getString(1));
				book.setBuyPrice(resultSet.getFloat(2));
				book.setSellPrice(resultSet.getFloat(3));
				book.setBookNum(resultSet.getInt(4));
				book.setDate(resultSet.getString(5));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		return bookList;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(null, preparedStatement, connection);
		}
	}

	/*
	@Override
	public <E> E getAnElement(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return (E) resultSet.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		return null;
	}
	*/
	
}


