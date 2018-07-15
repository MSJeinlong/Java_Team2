package com.game.TankGame;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyTools {
	public static Font f1 = new Font("宋体", Font.PLAIN, 14);
	public static Font f2 = new Font("黑体", Font.ITALIC, 12);
	public static Font f3 = new Font("黑体", Font.ROMAN_BASELINE, 16);
	public static Font f4 = new Font("宋体", Font.PLAIN, 12);
	public static Font f5 = new Font("黑体", Font.ROMAN_BASELINE, 20);
	public static Font f6 = new Font("黑体", Font.ROMAN_BASELINE, 30);

	// 设置图标大小的方法, 图标存放在JLabel里面
	public static JLabel setImage(JLabel jlb, ImageIcon img, int width, int height) {
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(img);
		jlb.setSize(width, height);
		return jlb;
	}

	// 泛型反射，通过反射机制获取，获取泛型的构造器，进而new 出实例
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
