package com.tools;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyTools {
	public static Font f1 = new Font("����", Font.PLAIN, 14);
	public static Font f2 = new Font("����", Font.ITALIC, 12);
	public static Font f3 = new Font("����", Font.ROMAN_BASELINE, 16);
	public static Font f4 = new Font("����", Font.PLAIN, 13);
	public static Font f5 = new Font("����", Font.ROMAN_BASELINE, 20);
	public static Font f6 = new Font("����", Font.ROMAN_BASELINE, 30);
	public static Font f7 = new Font("����", Font.PLAIN, 16);
	public static Font f8 = new Font("����", Font.PLAIN, 16);
	
	//public static JOptionPane.showMessageDialog(this, "��ѡ��һ��");

	// ����ͼ���С�ķ���, ͼ������JLabel����
	public static JLabel setImage(JLabel jlb, ImageIcon img, int width, int height) {
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(img);
		jlb.setSize(width, height);
		return jlb;
	}

}
