package com.test1;
/**
 * JTable的使用
 * 
 *
 */
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
public class Test1 extends JFrame{

	//rowData用来存放行数据, columnNames存放列名
	Vector rowData,  columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
	}
	
	//构造方法
	public Test1() {
		columnNames = new Vector();
		//设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData = new Vector();
		//rowData可以存放多行
		Vector hang = new Vector();
		hang.add("sp001");
		hang.add("孙悟空");
		hang.add("男");
		hang.add("500");
		hang.add("花果山");
		hang.add("少林派");
		
		//加入到rowData
		rowData.add(hang);
		
		//初始化JTable
		jt = new JTable(rowData, columnNames);
		
		//初始化jsp JScrollPane
		jsp = new JScrollPane(jt);
		
		//把jsp放到jframe
		this.add(jsp);
		
		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
