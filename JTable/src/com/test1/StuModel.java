package com.test1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.*;
/**
 * 
 * @author JunLong
 * 这是我的一个stu表的模型
 * 可以把对student表的各种操作封装到该类中
 */
public class StuModel extends AbstractTableModel {

	// rowData用来存放行数据, columnNames存放列名
	Vector rowData, columnNames;

	// 定义操作数据库需要的变量
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	//构造函数,用于初始化我们的数据模型
	public StuModel(){
		this.init("");
		
	}
	
	//通过传递的sql语句来更新表模型的数据
	public StuModel(String sql){
		this.init(sql);
	}
	
	public void init(String sql) {
		if(sql.equals("")) {
			sql = "select * from stu";
		}
		
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
			ps = ct.prepareStatement(sql);
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

				// 加入到rowData
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

	}
	
	public void addStu(String sql) {
		//根据用户sql语句添加数据
	}
	
	// 得到表模型的行数
	@Override
	public int getRowCount() {		
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// 得到表模型的列数
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub		
		return this.columnNames.size();
	}

	//得到列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	// 得到某行某列的数据
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

}
