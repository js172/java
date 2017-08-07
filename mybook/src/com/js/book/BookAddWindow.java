package com.js.book;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.js.bookcontrol.BookDataControl;
import com.js.datamodel.BookDataModel;

public class BookAddWindow extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlb1, jlb2, jlb3, jlb4;
	JTextField jtf1, jtf2, jtf3, jtf4;
	JPanel jp1,jp2,jp3;
	JButton jbt;
	BookDataControl dataControl;
	private static final String DB_BUY="buybook";
	
	public BookAddWindow(Frame owner, String title, boolean modal, BookDataModel bdm, int rowNum) {
		super(owner, title, modal);
		jlb1 = new JLabel("Book Name:");
		jlb2 = new JLabel("Price (Purchase):");
		jlb3 = new JLabel("Price (Sales):");
		jlb4 = new JLabel("Quantity (Purchase):");
		if(rowNum != -1){
			jtf1 = new JTextField((String) bdm.getValueAt(rowNum, 0));
			jtf1.setEditable(false);
			jtf2 = new JTextField(bdm.getValueAt(rowNum, 1).toString());
			jtf2.setEditable(false);
			jtf3 = new JTextField(bdm.getValueAt(rowNum, 2).toString());
			jtf3.setEditable(false);
		}else{
			jtf1 = new JTextField();
			jtf2 = new JTextField();
			jtf3 = new JTextField();
		}
		jtf4 = new JTextField();
		
		jbt = new JButton("Add");
		jbt.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(4, 1));
		jp2.setLayout(new GridLayout(4, 1));
		
		jp1.add(jlb1);
		jp1.add(jlb2);
		jp1.add(jlb3);
		jp1.add(jlb4);
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		
		jp3.add(jbt);
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setSize(300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt){
			doBookView();
		}
	}

	private void doBookView() {
		Book book = new Book();
		float buyPrice = 0;
		float sellPrice = 0;
		int bookNum = 0;
		try {
			buyPrice = Float.parseFloat(jtf2.getText());
			sellPrice = Float.parseFloat(jtf3.getText());
			bookNum = Integer.parseInt(jtf4.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Price or Amount should be a number!");
			return;
		}
		book.setBookName(jtf1.getText());
		book.setBuyPrice(buyPrice);
		book.setSellPrice(sellPrice);
		book.setBookNum(bookNum);
		dataControl = new BookDataControl();
		List<Book> booklist = dataControl.searchBook(book.getBookName());

		if(booklist.size()>0){
			Book bookFromDB = booklist.get(0);
			if(bookFromDB.getBuyPrice() != book.getBuyPrice() || bookFromDB.getSellPrice() != book.getSellPrice()){
				JOptionPane.showMessageDialog(this, "Cannot add books with same name but different price");
				return;
			}else{
				bookFromDB.setBookNum(bookFromDB.getBookNum()+book.getBookNum());
				dataControl.updateBook(bookFromDB);
				dataControl.addBookToDB(book,DB_BUY);
			}
		}else{
			dataControl.addNewBook(book);
			dataControl.addBookToDB(book,DB_BUY);
		}
		this.dispose();
		
	}

}
