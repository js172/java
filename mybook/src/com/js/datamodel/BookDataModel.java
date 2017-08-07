package com.js.datamodel;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.js.book.Book;
import com.js.book.BookManageWindow;
import com.js.book.DateSearch;
import com.js.bookcontrol.BookDataControl;

public class BookDataModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Object> rowData, columnNames;
	List<Book> bookList=null;
	BookDataControl dataControl = null;
	
	public void init(Object ... objs){
		columnNames = new Vector<Object>();
		columnNames.add("Book");
		columnNames.add("Price(Purchase)");
		columnNames.add("Price(Sales)");
		if(objs.length==2 && objs[1].getClass() == DateSearch.class){
			if(objs[0] == BookManageWindow.DB_BUYBOOK){
				columnNames.add("Quantity (Purchase)");
			}else if(objs[0] == BookManageWindow.DB_SELLBOOK){
				columnNames.add("Quantity (Sales)");
			}
			columnNames.add("Date");
		}else{
			columnNames.add("Quantity in Stock");
		}

		rowData = new Vector<Object>();
		if(objs.length==1){
			dataControl = new BookDataControl();
			bookList = dataControl.getBooks((String) objs[0]);
		}else if(objs.length==2 && objs[1].getClass() == DateSearch.class){
			dataControl = new BookDataControl();
			DateSearch dateSearch = (DateSearch) objs[1];
			bookList = dataControl.getBooks((String) objs[0],dateSearch.getDate());
		}else if(objs.length==2 && ((String)objs[1]).equalsIgnoreCase("search")){
				dataControl = new BookDataControl();
				bookList = dataControl.searchBook((String) objs[0]);
		}else if(objs.length==2 && ((String) objs[1]).equalsIgnoreCase("delete")){
				 dataControl = new BookDataControl();
				 dataControl.deleteBook((String) objs[0]);
				 bookList = dataControl.getBooks(BookManageWindow.DB_BOOK);
		}
		for(Book b:bookList){
			Vector<Object> data = new Vector<Object>();
			data.add(b.getBookName());
			data.add(b.getBuyPrice());
			data.add(b.getSellPrice());
			data.add(b.getBookNum());
			if(objs.length==2 && objs[1].getClass() != String.class){
				data.add(b.getDate());
			}
			rowData.add(data);
		}

	}
	
	public BookDataModel(String bookname,String handle){
		init(bookname, handle);
	}
	
	public BookDataModel(String db){
		init(db);
	}
	

	public BookDataModel(String db, DateSearch dateSearch) {
		// TODO Auto-generated constructor stub
		init(db,dateSearch);
	}


	@Override
	public int getRowCount() {
		return this.rowData.size();
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}


	@Override
	public String getColumnName(int column) {
		return (String) this.columnNames.get(column);
	}
	
	
}
