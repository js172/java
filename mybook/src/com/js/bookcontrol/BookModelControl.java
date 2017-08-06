package com.js.bookcontrol;

import com.js.datamodel.BookDataModel;

public class BookModelControl {
	BookDataModel bdm;
	public BookDataModel getBookDataModel(String db) {
		return new BookDataModel(db);
	}

	public BookDataModel getBookDataModel(String bookToSearch, String handleSearch) {
		return new BookDataModel(bookToSearch,handleSearch);
	}

}
