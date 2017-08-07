package com.js.Dao;

import java.util.List;

public interface DAO<T> {
	//get list of T(BOOK)
	List<T> getList(String sql,Object...args);
	
	//get an element
	<E> E getAnElement(String sql,Object...args);
	
	//insert, update, delete data in databases
	void update(String sql,Object...args);
}
