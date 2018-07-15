package com.test3;
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



	//构造函数,用于初始化我们的数据模型
	public StuModel(String sql){
		//SQLHelper sqlhelpr	
	}
	
	public StuModel(){
		
	}
	
	//添加学生(增、删、改)
	public static boolean updateStu(String sql, String[] paras) {
		boolean b = true;
		
		//创建SQLHepler(如果程序并发性不考虑，可以把SQLHelper做成static)
		SQLHelper sqlhelper = new SQLHelper();		
		return sqlhelper.updExecute(sql, paras);
	}
	
	//查询本质就是初始化
	public void queryStu(String sql, String []paras) {
		
		SQLHelper sqlhelper = null;
		
/*		if(sql.equals("")) {
			sql = "select * from student";
		}*/
		
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
		try {
			
			sqlhelper = new SQLHelper();
			ResultSet rs = null;
			if(paras.length == 0) {
				rs = sqlhelper.queryExecute(sql, paras);
			}
			else {
				rs = sqlhelper.queryExecute(sql);
			}
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
			sqlhelper.close();
		}

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
