package com.demo2;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author JunLong Model�࣬�߶ȳ��󣬷���������ͬ��Model����UserModel,������Model
 */
public class Model extends AbstractTableModel {
	// rowData�������������, columnNames�������
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

	// ��ѯ���������ｻ������ȥ���
	public int query(String name) {
		return 0;
	}

	// ������ӶԻ���,���ｻ������ȥ���
	public void updateDialog(Model m, int rowNum) {

	}

	/*
	 * // ����ɾ���ĵķ��������ｻ������ȥ��� public static int update(String[] paras, int rowNum) {
	 * System.out.println("���෽��"); return 0; }
	 */

	// ���
	public int add(String[] paras) {
		return 0;

	}

	// �޸�
	public int alter(String[] paras, int rowNum) {
		return 0;
	}

	// ɾ��
	public void delete(int rowNum) {

	}

	// �Ӵ��̶�ȡ�û���Ϣ
	public static void readData() {
		// Group = FileHelper.readData(filePath);
	}

	// ���û����ݱ��浽����
	public static void writeData() {

	}

	// �õ���ģ�͵�����
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// �õ���ģ�͵�����
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// �õ�ĳ��ĳ�е�����
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

	// �õ�����
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columnNames.get(column).toString();
	}

}
