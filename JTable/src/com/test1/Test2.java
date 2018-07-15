package com.test1;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;

/**
 * 
 * 从数据库里取出信息
 */
public class Test2 extends JFrame {

	// rowData用来存放行数据, columnNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// 定义操作数据库需要的变量
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 tests = new Test2();
	}

	// 构造方法
	public Test2() {
		columnNames = new Vector();
		// 设置JTable列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");

		rowData = new Vector();
		// 连接SQL Server数据库
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// 数据源 ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "junlong365";
		try {
			// 加载驱动
			Class.forName(driverName);
			ct = DriverManager.getConnection(dbURL, Name, Pwd);
			ps = ct.prepareStatement("select * from stu");
			rs = ps.executeQuery();
			System.out.println("连接数据库成功");

			while (rs.next()) {
				// rowData可以存放多行
				Vector hang = new Vector();
				// 获取数据库 stu表 的数据
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				//加入到rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
		} finally {
			// 关闭
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (ct != null)
					ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 初始化JTable
		jt = new JTable(rowData, columnNames);

		// 初始化jsp JScrollPane
		jsp = new JScrollPane(jt);

		// 把jsp放到jframe
		this.add(jsp);

		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
