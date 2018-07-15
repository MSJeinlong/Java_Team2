package com.test1;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author JunLong 完成一个Mini版的学生管理系统
 */
public class Test3 extends JFrame implements ActionListener {

	// 定义一些控件
	JPanel jp1, jp2;
	JLabel jlb1;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;

	// rowData用来存放行数据, columnNames存放列名
	Vector rowData, columnNames;

	// 定义操作数据库需要的变量
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test3 test3 = new Test3();
	}

	public Test3() {
		// 创建控件
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jlb1 = new JLabel("请输入名字");

		// 把各个控件加入到jp1
		jp1.add(jlb1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp2 = new JPanel();

		jb2 = new JButton("添加");
		jb3 = new JButton("修改");
		jb4 = new JButton("删除");

		// 为按钮注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("query");
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3.addActionListener(this);
		jb3.setActionCommand("alter");
		jb4.addActionListener(this);
		jb4.setActionCommand("delete");

		// 把各个按钮加入到jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		//创建一个数据模型对象
		StuModel sm = new StuModel();
		
		// 初始化JTable
		jt = new JTable(sm);

		// 初始化jsp JScrollPane
		jsp = new JScrollPane(jt);

		// 把jp1、jsp、jp2放到jframe
		this.add(jp1, "North");
		this.add(jsp);
		this.add(jp2, "South");

		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		//用户点击查询
		case "query":
			System.out.println("查询");
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name = this.jtf.getText().trim();
			//写一个sql语句
			String sql = "select * from stu where stuName='"+name+"'";
			//构建新的数据模型类，并更新
			StuModel sm = new StuModel(sql);
			//更新JTable
			jt.setModel(sm);
			break;
		//用户点击添加
		case "add":
			StuAddDialog sa = new StuAddDialog(this, "添加学生", true);
			//重新再获得新的数据模型
			break;
		case "alter":
			break;
		case "delete":
			break;
		}
	}
}
