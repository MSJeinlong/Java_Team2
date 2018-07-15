package com.test3;

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
 * @author JunLong 
 * 完成一个Mini版的学生管理系统 
 * 1、查询 
 * 2、添加 
 * 3、删除 
 * 4、修改
 * 对数据的处理基于文件IO实现
 */
public class StuManage extends JFrame implements ActionListener {

	// 定义一些控件
	JPanel jp1, jp2;
	JLabel jlb1;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	String sql = null;

	// rowData用来存放行数据, columnNames存放列名
	Vector rowData, columnNames;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test3 = new StuManage();
	}

	public StuManage() {
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

		// 创建一个数据模型对象
		sm = new StuModel();
		String[] paras = new String[0];
		sm.queryStu("select * from student", paras);

		// 初始化JTable
		jt = new JTable(sm);

		// 初始化jsp JScrollPane
		jsp = new JScrollPane(jt);

		// 把jp1、jsp、jp2放到jframe
		this.add(jp1, "North");
		this.add(jsp);
		this.add(jp2, "South");

		this.setBounds(700, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] paras2 = new String[0];
		switch (e.getActionCommand()) {

		// 用户点击查询
		case "query":
			System.out.println("查询");
			// 因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name = this.jtf.getText().trim();
			// 写一个sql语句
			String sql = "select * from student where stuName like '"+name+"%'";
			String[] paras = {name};
			// 构建新的数据模型类，并更新
			sm = new StuModel();
			sm.queryStu(sql, paras);
			// 更新JTable
			jt.setModel(sm);
			break;

		// 用户点击添加
		case "add":
			StuAddDialog sa = new StuAddDialog(this, "添加学生", true);
			// 重新再获得新的数据模型
			sm = new StuModel();
			sm.queryStu("select * from student", paras2);
			// 更新JTable
			jt.setModel(sm);
			break;

		case "alter":
			// 用户希望修改
			System.out.println("用户希望修改");
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 用户没选择任何一行时提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}

			// 显示修改对话框
			new StuUpdateDialog(this, "修改学生信息", true, sm, rowNum);
			// 重新再获得新的数据模型
			sm = new StuModel();
			sm.queryStu("select * from student", paras2);
			// 更新JTable
			jt.setModel(sm);
			break;

		// 用户希望删除某个记录
		case "delete":
			// 1、得到该学生的id
			// getSelectedRow会返回用户点击的行
			// 如果该用户一行都没有选中，就会返回-1
			int rowNum1 = this.jt.getSelectedRow();
			if (rowNum1 == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			// 得到学生编号
			String stuId = (String) sm.getValueAt(rowNum1, 0);

			// 创建一个sql语句
			sql = "delete from student where stuId = ?";
			String[] paras1 = { stuId };
			// StuModel temp = new StuModel();
			StuModel.updateStu(sql, paras1);
			// 更新数据库
			sm = new StuModel();		
			sm.queryStu("select * from student", paras2);
			jt.setModel(sm);
			break;
		}
	}
}
