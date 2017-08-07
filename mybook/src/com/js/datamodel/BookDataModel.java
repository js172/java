package com.js.datamodel;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.js.book.Book;
import com.js.book.BookManageWindow;
import com.js.bookcontrol.BookDataControl;

public class BookDataModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Object> rowData, columnNames;
	List<Book> bookList=null;
	BookDataControl dataControl = null;
	
	public void init(String ... strs){
		columnNames = new Vector<Object>();
		columnNames.add("Book");
		columnNames.add("Price(Purchase)");
		columnNames.add("Price(Sales)");
		if(strs.length==1 && strs[0] != BookManageWindow.DB_BOOK){
			if(strs[0] == BookManageWindow.DB_BUYBOOK){
				columnNames.add("Quantity (Purchase)");
			}else if(strs[0] == BookManageWindow.DB_SELLBOOK){
				columnNames.add("Quantity (Sales)");
			}
			columnNames.add("Date");
		}else{
			columnNames.add("Quantity in Stock");
		}
		rowData = new Vector<Object>();
		if(strs.length==1){
			dataControl = new BookDataControl();
			bookList = dataControl.getBooks(strs[0]);
		}else if(strs.length==2 && strs[1].equalsIgnoreCase("search")){
				dataControl = new BookDataControl();
				bookList = dataControl.searchBook(strs[0]);
		}else if(strs.length==2 && strs[1].equalsIgnoreCase("delete")){
				 dataControl = new BookDataControl();
				 dataControl.deleteBook(strs[0]);
				 bookList = dataControl.getBooks(BookManageWindow.DB_BOOK);
		}
		for(Book b:bookList){
			Vector<Object> data = new Vector<Object>();
			data.add(b.getBookName());
			data.add(b.getBuyPrice());
			data.add(b.getSellPrice());
			data.add(b.getBookNum());
			if(strs.length==1 && strs[0] != BookManageWindow.DB_BOOK){
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
