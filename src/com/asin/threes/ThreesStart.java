package com.asin.threes;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
/*
	作者：Asin欧米茄
	手机：18124295040
 */
public class ThreesStart {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setBounds(10, 10, 400, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.add(new ThreesGamePanel());
		frame.setVisible(true);
	}
}
