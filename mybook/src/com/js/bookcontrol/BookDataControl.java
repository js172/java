package com.js.bookcontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.js.Dao.BookDao;
import com.js.book.Book;
import com.js.book.BookManageWindow;

public class BookDataControl {
	BookDao bookDao=null;
	public void deleteBook(String bookname){
		String sql = "DELETE FROM book WHERE bookname=?";
		bookDao = new BookDao();
		bookDao.update(sql, bookname);
	}
	
	public void addNewBook(Book book){
		String sql = "INSERT INTO book (bookname,buyprice,sellprice,booknum) VALUES (?,?,?,?)";
		bookDao = new BookDao();
		bookDao.update(sql, book.getBookName(), book.getBuyPrice(), book.getSellPrice(), book.getBookNum());
	}

	public List<Book> getBooks(String db){
		if(db.equalsIgnoreCase(BookManageWindow.DB_BOOK)){
			String sql = "SELECT * FROM " + db;
			bookDao = new BookDao();
			return bookDao.getList(sql);
		}else{
			String sql = "SELECT * FROM " + db + " WHERE DATE(time) = ?";
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String today = dateFormat.format(date);
			bookDao = new BookDao();
			return bookDao.getList(sql,today);
		}
	}
	
	public List<Book> searchBook(String book){
		String sql = "SELECT * FROM book WHERE bookname LIKE ?";
		bookDao = new BookDao();
		return bookDao.getList(sql,"%" + book +"%");
	}
	
	public void updateBook(Book book){
		String sql = "UPDATE book SET bookname = ?,"
					+ "buyprice = ?, sellprice = ?, booknum = ? WHERE bookname = ?";
		bookDao = new BookDao();
		bookDao.update(sql,book.getBookName(),book.getBuyPrice(),book.getSellPrice(),book.getBookNum(),book.getBookName());
	}

	public void addBookToDB(Book book,String db) {
		String sql = "INSERT INTO "+db+" (bookname,buyprice,sellprice,booknum) VALUES (?,?,?,?)";
		bookDao = new BookDao();
		bookDao.update(sql, book.getBookName(), book.getBuyPrice(),book.getSellPrice(), book.getBookNum());
	}

}
