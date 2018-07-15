package com.demo2;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author JunLong Model类，高度抽象，方便管理管理不同的Model，如UserModel,销售商Model
 */
public class Model extends AbstractTableModel {
	// rowData用来存放行数据, columnNames存放列名
	protected Vector<Vector<String>> rowData = new Vector();;
	protected Vector<String> columnNames = new Vector();

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}

	public Vector<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(Vector<String> columnNames) {
		this.columnNames = columnNames;
	}

	public Model() {

	}

	// 查询方法，这里交给子类去完成
	public int query(String name) {
		return 0;
	}

	// 弹出添加对话框,这里交给子类去完成
	public void updateDialog(Model m, int rowNum) {

	}

	/*
	 * // 增、删、改的方法，这里交给子类去完成 public static int update(String[] paras, int rowNum) {
	 * System.out.println("父类方法"); return 0; }
	 */

	// 添加
	public int add(String[] paras) {
		return 0;

	}

	// 修改
	public int alter(String[] paras, int rowNum) {
		return 0;
	}

	// 删除
	public void delete(int rowNum) {

	}

	// 从磁盘读取用户信息
	public static void readData() {
		// Group = FileHelper.readData(filePath);
	}

	// 将用户数据保存到磁盘
	public static void writeData() {

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

	// 得到某行某列的数据
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

	// 得到列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columnNames.get(column).toString();
	}

}
