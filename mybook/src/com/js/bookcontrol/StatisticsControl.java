package com.js.bookcontrol;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.swing.JTable;

import com.js.book.BookManageWindow;
import com.js.datamodel.BookDataModel;

public class StatisticsControl {
	public static List<Float> getStatistics(BookDataModel bdm, JTable jtb, String statistic) {
		float netProf = 0f;
		float totalPrice = 0f;
		List<Float> list = new ArrayList<>();
		for(int i=0;i<jtb.getRowCount();i++){
			float buyPrice = (float)bdm.getValueAt(i, 1);
			float sellPrice = (float)bdm.getValueAt(i, 2);
			int num = (int)bdm.getValueAt(i, 3);
			if(statistic.equalsIgnoreCase(BookManageWindow.STATISTIC_PROF)){
				netProf = netProf + (sellPrice*100-buyPrice*100)*num/100;
				totalPrice = totalPrice + sellPrice*num;
			}else
				totalPrice = totalPrice + buyPrice*num;

		}
		netProf=Float.parseFloat(new Formatter().format("%.2f", netProf).toString());
		list.add(netProf);
		list.add(totalPrice);
		return list;
	}

}
