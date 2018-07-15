package com.game.TankGame;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyTools {
	public static Font f1 = new Font("����", Font.PLAIN, 14);
	public static Font f2 = new Font("����", Font.ITALIC, 12);
	public static Font f3 = new Font("����", Font.ROMAN_BASELINE, 16);
	public static Font f4 = new Font("����", Font.PLAIN, 12);
	public static Font f5 = new Font("����", Font.ROMAN_BASELINE, 20);
	public static Font f6 = new Font("����", Font.ROMAN_BASELINE, 30);

	// ����ͼ���С�ķ���, ͼ������JLabel����
	public static JLabel setImage(JLabel jlb, ImageIcon img, int width, int height) {
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(img);
		jlb.setSize(width, height);
		return jlb;
	}

	// ���ͷ��䣬ͨ��������ƻ�ȡ����ȡ���͵Ĺ�����������new ��ʵ��
	public static <T> T newTclass(Class<T> clazz) {
		T a = null;
		try {
			a = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
