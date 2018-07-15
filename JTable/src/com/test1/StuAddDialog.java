package com.test1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener{
	
	//定义我需要的组件
	JLabel[] jlb = new JLabel[5];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[5];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	
	//owner它的父窗口
	//title 窗口名
	//model 指定是模式窗口，还是非模式的窗口
	public StuAddDialog(Frame owner, String title, boolean model) {
		//调用父类构造方法，达到模式对话框效果
		super(owner,title,model);
		jlb[0] = new JLabel("学号：");
		jlb[1] = new JLabel("姓名：");
		jlb[2] = new JLabel("性别：");
		jlb[3] = new JLabel("生日：");
		jlb[4] = new JLabel("记过次数：");
		
		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		
		//两个单选框
		jrb1 = new JRadioButton("男");
		jrb2 = new JRadioButton("女");
		
		jb1 = new JButton("添加");
		jb2 = new JButton("取消");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		//因为单选两个不能同时选择，所有加到一个组里可以达到效果
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);  
        group.add(jrb2);
        box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(2));
        box.add(jrb1);
        box.add(jrb2);
	
		//添加组件
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		
		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(box);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		//jp2.add(jtf[4]);
		
		jp3.add(jb1);
		jp3.add(jb2);
			
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		
		//展现
		this.setBounds(500, 400, 300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
