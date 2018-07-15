package com.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author JunLong 这是一个对数据库进行操作的类（SqlHelper）
 */
public class SQLHelper {
	// 定义操作数据库需要的变量
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
	String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// 数据源 ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
	String UserName = "sa";
	String Passwd = "junlong365";

	// 查询数据库的操作
	public ResultSet queryExecute(String sql, String[] paras) {
		try {

			// 连接数据库
			this.ConnectDatabase(sql, paras);

			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 关闭资源
		}
		return rs;
	}

	// 无参查询

	public ResultSet queryExecute(String sql) {
		try {

			Class.forName(driverName);
			// 2、得到连接
			ct = DriverManager.getConnection(dbURL, UserName, Passwd);
			// 3、创建ps
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 关闭资源
		}
		return rs;
	}

	// 添加学生(增、删、改)
	public boolean updExecute(String sql, String[] paras) {
		boolean b = true;
		try {
			// 连接数据库
			this.ConnectDatabase(sql, paras);
			// 执行操作
			if (ps.executeUpdate() != -1) {
				b = false;
			}

		} catch (Exception e) {
			b = false;
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.close();
		}
		return b;
	}

	// 连接数据库资源
	public void ConnectDatabase(String sql, String[] paras) {
		// 1、加载驱动
		try {
			Class.forName(driverName);
			// 2、得到连接
			ct = DriverManager.getConnection(dbURL, UserName, Passwd);
			// 3、创建ps
			ps = ct.prepareStatement(sql);
			// 给 ps 参数( ? )赋值
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭数据库资源
	public void close() {
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
}
