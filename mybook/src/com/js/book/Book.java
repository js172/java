package com.js.book;

public class Book {
	private String bookName;
	private float buyPrice;
	private float sellPrice;
	private int bookNum;
	private String date;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Book(String bookName, float buyPrice,float sellPrice, int bookNum,String date) {
		super();
		this.bookName = bookName;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.bookNum = bookNum;
		this.date = date;
	}
	public Book(String bookName, float buyPrice,float sellPrice, int bookNum) {
		super();
		this.bookName = bookName;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.bookNum = bookNum;
	}
	public Book(){
		
	}
	
	
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", bookNum=" + bookNum + "]";
	}
	
}
