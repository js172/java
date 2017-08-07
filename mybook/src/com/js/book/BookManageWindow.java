package com.js.book;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.js.bookcontrol.BookModelControl;
import com.js.bookcontrol.StatisticsControl;
import com.js.datamodel.BookDataModel;

public class BookManageWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	JLabel jlb1,jlb2,jlb3;
	JButton jbt1,jbt2,jbt3,jbt4,jbt5,jbt6,jbt7;
	JTable jtb;
	JScrollPane jsp1;
	JTextField jtf;
	BookDataModel bdm;
	BookModelControl bookModelControl;
	public static final String HANDLE_SEARCH = "search";
	public static final String HANDLE_DELETE = "delete";
	public static final String DB_BUYBOOK = "buybook";
	public static final String DB_SELLBOOK = "sellbook";
	public static final String DB_BOOK = "book";
	public static final String STATISTIC_COST = "cost";
	public static final String STATISTIC_PROF = "prof";
	List<Float> list;
	Date date;
	DateFormat dateFormat;

	public BookManageWindow(){
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jbt1 = new JButton("search");
		jbt1.addActionListener(this);
		jbt5 = new JButton("Main Page");
		jbt5.addActionListener(this);
		jlb1 = new JLabel("Book name:");
	
		jp1.add(jlb1);
		jp1.add(jtf);
		jp1.add(jbt1);
		jp1.add(jbt5);
		
		jp2 = new JPanel();
		jbt2 = new JButton("Add");
		jbt2.addActionListener(this);
		jbt3 = new JButton("Sell");
		jbt3.addActionListener(this);
//		jbt4 = new JButton("Delete");
//		jbt4.addActionListener(this);
		jbt6 = new JButton("Today's Cost");
		jbt6.addActionListener(this);
		jbt7 = new JButton("Today's Profit");
		jbt7.addActionListener(this);
		
		jp2.add(jbt2);
		jp2.add(jbt3);
//		jp2.add(jbt4);
		jp2.add(jbt6);
		jp2.add(jbt7);
		
		bookModelControl = new BookModelControl();
		bdm = bookModelControl.getBookDataModel(DB_BOOK);
		jtb = new JTable(bdm);
		
		jp3=new JPanel();
		jlb2 = new JLabel("Main Page");
		jlb2.setBounds(350, 0, 100, 50);
		jsp1=new JScrollPane(jtb);
		jsp1.setBounds(0, 50, 800, 340);
		jlb3 = new JLabel();
		jlb3.setBounds(450, 400, 350, 50);
		
		jp3.setLayout(null);
		jp3.add(jsp1);
		jp3.add(jlb2);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2,BorderLayout.SOUTH);
		this.add(jp3);

		this.setSize(800, 550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt1){
			//search a book
			jlb2.setText("Search Results");
			String bookToSearch = this.jtf.getText();
			bdm = bookModelControl.getBookDataModel(bookToSearch,HANDLE_SEARCH);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,"");
		}else if(e.getSource()==jbt2){
			//buy books from book store
			int rowNum = this.jtb.getSelectedRow();
			new BookAddWindow(this,"Add books",true,bdm,rowNum);
			jlb2.setText("Main Page");
			bdm = bookModelControl.getBookDataModel(DB_BOOK);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,"");
		}else if(e.getSource()==jbt3){
			//sell books to client
			int rowNum = this.jtb.getSelectedRow();
			if(rowNum == -1){
				JOptionPane.showMessageDialog(this, "Please select a book to sell");
				return;
			}
			new BookSellWindow(this,"Sell books",true,bdm,rowNum);
			jlb2.setText("Main Page");
			bdm = bookModelControl.getBookDataModel(DB_BOOK);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,"");
		}else if(e.getSource()==jbt4){
			//delete a book
			int rowNum = this.jtb.getSelectedRow();
			if(rowNum == -1){
				JOptionPane.showMessageDialog(this, "Please select a book to delete");
				return;
			}
			String bookToDelete = (String) bdm.getValueAt(rowNum, 0);
			bdm = bookModelControl.getBookDataModel(bookToDelete,HANDLE_DELETE);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,"");
		}else if(e.getSource()==jbt5){
			//to the main book view
			jlb2.setText("Main Page");
			bdm = bookModelControl.getBookDataModel(DB_BOOK);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,"");
		}else if(e.getSource()==jbt6){
			//list books were added today
			jlb2.setText("Today's Cost");
			bdm = bookModelControl.getBookDataModel(DB_BUYBOOK);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,STATISTIC_COST);
		}else if(e.getSource()==jbt7){
			//list books were sold today
			jlb2.setText("Today's Profit");
			bdm = bookModelControl.getBookDataModel(DB_SELLBOOK);
			jtb.setModel(bdm);
			statisticsViewInit(bdm,jtb,STATISTIC_PROF);
		}
	}

	private void statisticsViewInit(BookDataModel bdm2, JTable jtb2, String statisticProf) {
		if(statisticProf.length() < 1){
			jlb3.setText("");
		}else{
			jp3.add(jlb3);
			list = StatisticsControl.getStatistics(bdm,jtb,statisticProf);
			if(statisticProf.equalsIgnoreCase(STATISTIC_COST)){
				jlb3.setText("Today's Total Cost : " + list.get(1).toString());
			}else if(statisticProf.equalsIgnoreCase(STATISTIC_PROF)){
				jlb3.setText("Today's Gross Sales:" + list.get(1).toString() + "  Net Profit:" +  list.get(0).toString());
			}
		}
		

	}

}
