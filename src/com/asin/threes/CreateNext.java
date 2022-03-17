package com.asin.threes;

import java.util.ArrayList;

public class CreateNext {

	Integer createNextNum(int maxNum , int num1, int num2) {
		int createNum;
		ArrayList<Integer> arr = new ArrayList<>();
		if(maxNum>=7) {
			createNum = (int)(Math.random()*20)+1;
			if(createNum>=20) {
				for(int i=0,j=maxNum;i<3&&j>=7;i++,j--) {
					arr.add(j-3);
				}
			}else {
				arr.add(3);
				arr.add(3);
				if(num1>=3&&num2<4) {
					arr.add(2);
				}else if(num2>=3&&num1<4) {
					arr.add(1);
				}else if (num1<4&&num2<4) {
					arr.add(1);
					arr.add(2);
				}
			}
		}else {
			arr.add(3);
			arr.add(3);
			if(num1>=3&&num2<4) {
				arr.add(2);
			}else if(num2>=3&&num1<4) {
				arr.add(1);
			}else if(num1<4&&num2<4) {
				arr.add(1);
				arr.add(2);
			}
		}
		return createNextNumByArr(arr);
	}

	Integer createNextNumByArr(ArrayList<Integer> arr) {
		// TODO Auto-generated method stub
		return arr.get((int)(Math.random()*arr.size()));
	}
}
