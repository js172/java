package com.js.Dao;

import java.util.List;

public class DaoImpl<T> implements DAO<T> {

	@Override
	public List<T> getList(String sql, Object... args) {
		return null;
	}

	@Override
	public void update(String sql, Object... args) {
		
	}

	@Override
	public <E> E getAnElement(String sql, Object... args) {
		return null;
	}

}



