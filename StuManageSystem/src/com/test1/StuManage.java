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
 * @author JunLong 
 * 完成一个Mini版的学生管理系统
 * 1、查询
 * 2、添加
 * 3、修改
 * 4、删除
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

	// rowData用来存放行数据, columnNames存放列名
	Vector rowData, columnNames;

	// 定义操作数据库需要的变量
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

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

		//创建一个数据模型对象
		 sm = new StuModel();
		
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
		switch (e.getActionCommand()) {
		
		//用户点击查询
		case "query":
			System.out.println("查询");
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name = this.jtf.getText().trim();
			//写一个sql语句
			String sql = "select * from student where stuName like '"+name+"%'";
			//构建新的数据模型类，并更新
			sm = new StuModel(sql);
			//更新JTable
			jt.setModel(sm);
			break;
			
		//用户点击添加
		case "add":
			StuAddDialog sa = new StuAddDialog(this, "添加学生", true);
			//重新再获得新的数据模型
			 sm = new StuModel();
			//更新JTable
			jt.setModel(sm);
			break;
			
		case "alter":
			//用户希望修改
			System.out.println("用户希望修改");
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				//用户没选择任何一行时提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			
			//显示修改对话框
			new StuUpdateDialog(this, "修改学生信息",true, sm, rowNum);
			//重新再获得新的数据模型
			 sm = new StuModel();
			//更新JTable
			jt.setModel(sm);
			break;
			
		//用户希望删除某个记录
		case "delete":
			//1、得到该学生的id
			//getSelectedRow会返回用户点击的行
			//如果该用户一行都没有选中，就会返回-1
			int rowNum1 = this.jt.getSelectedRow();
			if(rowNum1 == -1) {
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			//得到学生编号
			String stuId = (String)sm.getValueAt(rowNum1, 0);
			//System.out.println("id:"+stuId);
			//连接数据库完成删除任务
			try {
				//连接SQL数据库
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
				String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// 数据源 ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
				String Name = "sa";
				String Pwd = "junlong365";
				
				Class.forName(driverName);
				ct = DriverManager.getConnection(dbURL, Name, Pwd);
				ps = ct.prepareStatement("delete from student where stuid=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
				
			} catch(Exception e1) {
				e1.printStackTrace();
			} finally {
				//关闭资源
				try {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
					if (ct != null)
						ct.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			//更新数据库
			sm = new StuModel();
			jt.setModel(sm);
			break;
		}
	}
}
