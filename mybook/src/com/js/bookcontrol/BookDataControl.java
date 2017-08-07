package com.js.bookcontrol;

import java.util.List;

import com.js.Dao.BookDao;
import com.js.book.Book;

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
			String sql = "SELECT * FROM " + db;
			bookDao = new BookDao();
			return bookDao.getList(sql);
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

	public List<Book> getBooks(String db, String date) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM " + db + " WHERE DATE(time) LIKE ?";
		bookDao = new BookDao();
		return bookDao.getList(sql,"%"+date+"%");
	}

}
