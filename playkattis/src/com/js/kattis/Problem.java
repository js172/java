package com.js.kattis;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem {
	/*
	 * Kattis:Selling Spatulas
	 */
    private static Scanner sc;
    private static ArrayList<Integer> timeList;
    private static ArrayList<Float> salesList;
    private static final float COST_PER_MIN=0.08f;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        while(sc.hasNext()){ 
            int num = sc.nextInt();
            if(num==0){
                break;
            }else{
                timeList=new ArrayList<Integer>();
                salesList=new ArrayList<Float>();
                findMaxBenefit(num);
            }
        }
        sc.close();
    }

    private static void findMaxBenefit(int num) {
        float maxProf = 0;
        int iMax = 0;
        int jMax = 0;
        int lenMax = 0;
        
        for(int i=0;i<num;i++){
            int t = sc.nextInt();
            float f = sc.nextFloat();
            timeList.add(t);
            salesList.add(f);
            if(f-COST_PER_MIN > maxProf){
                maxProf = f-COST_PER_MIN;
                iMax=i;
                jMax=i;
                lenMax=1;
            }
        }

        for(int i = 0; i < salesList.size()-1; i++){
            float value = 0;
            float sum = salesList.get(i);
            for(int j = i+1; j < salesList.size(); j++){
                sum += salesList.get(j);
                value = sum-(timeList.get(j)+1-timeList.get(i))*COST_PER_MIN;
                value = (float)(Math.round(value*100))/100;
                if(value==maxProf && (j-i)<lenMax){
                    maxProf = value;
                    iMax=i;
                    jMax=j;
                    lenMax = timeList.get(j)-timeList.get(i);
                }
                if(value > maxProf){
                    maxProf = value;
                    iMax=i;
                    jMax=j;
                    lenMax = timeList.get(j)-timeList.get(i);
                }
            }
        }

        if(maxProf==0){
            System.out.println("no profit");
        }else{
            DecimalFormat fnum  = new DecimalFormat("##0.00");
            String res=fnum.format(maxProf);
            System.out.println(res+" "+timeList.get(iMax)+" "+timeList.get(jMax));
        }
    }
}