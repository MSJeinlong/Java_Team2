package com.tools;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyTools {
	public static Font f1 = new Font("宋体", Font.PLAIN, 14);
	public static Font f2 = new Font("黑体", Font.ITALIC, 12);
	public static Font f3 = new Font("黑体", Font.ROMAN_BASELINE, 16);
	public static Font f4 = new Font("宋体", Font.PLAIN, 13);
	public static Font f5 = new Font("黑体", Font.ROMAN_BASELINE, 20);
	public static Font f6 = new Font("黑体", Font.ROMAN_BASELINE, 30);
	public static Font f7 = new Font("宋体", Font.PLAIN, 16);
	public static Font f8 = new Font("黑体", Font.PLAIN, 16);
	
	//public static JOptionPane.showMessageDialog(this, "请选择一行");

	// 设置图标大小的方法, 图标存放在JLabel里面
	public static JLabel setImage(JLabel jlb, ImageIcon img, int width, int height) {
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(img);
		jlb.setSize(width, height);
		return jlb;
	}

}
