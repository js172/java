package com.js.book;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.js.bookcontrol.BookDataControl;
import com.js.datamodel.BookDataModel;

public class BookSellWindow extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlb1;
	JTextField jtf1;
	JPanel jp1,jp2;
	JButton jbt1;
	BookDataControl dataControl;
	BookDataModel bookDataModel;
	int rownum;
	
	public BookSellWindow(Frame owner, String title, boolean modal, BookDataModel bdm,int rowNum) {
		super(owner, title, modal);
		bookDataModel=bdm;
		rownum=rowNum;
		jlb1 = new JLabel("Quantity (Sales)");
		jtf1 = new JTextField(10);
		jbt1 = new JButton("confirm");
		jbt1.addActionListener(this);
		
		jp1 = new JPanel();
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2 = new JPanel();
		jp2.add(jbt1);
		
		this.add(jp1,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setSize(300, 100);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt1){
			int num = Integer.parseInt(jtf1.getText());
			int bookRemain = (int)bookDataModel.getValueAt(rownum, 3)-num;
			if(bookRemain >= 0){
				Book book = new Book();
				book.setBookName((String)bookDataModel.getValueAt(rownum, 0));
				book.setBuyPrice((float)bookDataModel.getValueAt(rownum, 1));
				book.setSellPrice((float)bookDataModel.getValueAt(rownum, 2));
				book.setBookNum(bookRemain);
				dataControl = new BookDataControl();
				dataControl.updateBook(book);
				book.setBookNum(num);
				dataControl.addBookToDB(book,BookManageWindow.DB_SELLBOOK);
			}else{
				JOptionPane.showMessageDialog(this, "Not enough for sales!");
				return;
			}
				
			this.dispose();
		}
		
	}
}
