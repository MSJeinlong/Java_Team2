package com.test3;

/**
 * 修改学生信息
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class StuUpdateDialog extends JDialog implements ActionListener {

	// 定义我需要的组件
	JLabel[] jlb = new JLabel[6];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[5];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	String sex = null;

	// owner它的父窗口
	// title 窗口名
	// model 指定是模式窗口，还是非模式的窗口
	public StuUpdateDialog(Frame owner, String title, boolean model, StuModel sm, int rowNum) {
		// 调用父类构造方法，达到模式对话框效果
		super(owner, title, model);
		jlb[0] = new JLabel("学号：");
		jlb[1] = new JLabel("姓名：");
		jlb[2] = new JLabel("性别：");
		jlb[3] = new JLabel("年龄：");
		jlb[4] = new JLabel("籍贯：");
		jlb[5] = new JLabel("系别：");

		jtf[0] = new JTextField();
		// 初始化数据
		jtf[0].setText((String) sm.getValueAt(rowNum, 0));
		//让jtf[0]不能修改
		jtf[0].setEditable(false);
		jtf[1] = new JTextField();
		jtf[1].setText((String) sm.getValueAt(rowNum, 1));
		jtf[2] = new JTextField();
		jtf[2].setText(sm.getValueAt(rowNum, 3).toString());
		jtf[3] = new JTextField();
		jtf[3].setText((String) sm.getValueAt(rowNum, 4));
		jtf[4] = new JTextField();
		jtf[4].setText((String) sm.getValueAt(rowNum, 5));
		// jtf[5] = new JTextField();

		// 两个单选框
		sex = (String) sm.getValueAt(rowNum, 2);	//获取原来的性别信息
		jrb1 = new JRadioButton("男");
		jrb2 = new JRadioButton("女");
		//还原单选按钮的选择状态
		if(sex.equals("男")) {
			jrb1.setSelected(true);			
		} else {
			jrb2.setSelected(true);
		}
		
		jb1 = new JButton("确定");
		jb2 = new JButton("取消");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// 因为单选两个不能同时选择，所有加到一个组里可以达到效果
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(2));
		box.add(jrb1);
		box.add(jrb2);

		// 添加组件
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);

		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(box);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);

		jp3.add(jb1);
		jp3.add(jb2);

		// 注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// JRadioButton注册监听
		// jrb1.addItemListener(this);
		jrb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb1.getText();
				System.out.println("sex:" + sex);
			}

		});

		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb2.getText();
				System.out.println("sex:" + sex);
			};

		});

		// System.out.println("sex:"+sex);
		// 设置布局
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// 展现
		this.setBounds(1000, 400, 300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 定义操作数据库需要的变量
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		switch (e.getActionCommand()) {
		case "confirm":
			//创建一个sql语句与编译对象
			//System.out.println(sex);
			String sql = "update student set stuName = ?,"
					+ "stuSex = ?, stuAge = ?, stuJg = ?, stuDept = ? where stuId = ?";
			String []paras = {jtf[1].getText(), sex, jtf[2].getText(), jtf[3].getText(), jtf[4].getText(), jtf[0].getText()};
			//StuModel temp = new StuModel();
			StuModel.updateStu(sql, paras);
			this.dispose();
			break;
		case "canel":
			this.dispose();
			break;
		}
	}
}
