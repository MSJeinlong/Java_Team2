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
 * �����ҵ�һ��stu���ģ��
 * ���԰Ѷ�student��ĸ��ֲ�����װ��������
 */
public class StuModel extends AbstractTableModel {

	// rowData�������������, columnNames�������
	Vector rowData, columnNames;

	// ����������ݿ���Ҫ�ı���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	//���캯��,���ڳ�ʼ�����ǵ�����ģ��
	public StuModel(){
		this.init("");
		
	}
	
	//ͨ�����ݵ�sql��������±�ģ�͵�����
	public StuModel(String sql){
		this.init(sql);
	}
	
	public void init(String sql) {
		if(sql.equals("")) {
			sql = "select * from stu";
		}
		
		columnNames = new Vector();
		// ����JTable����
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");

		rowData = new Vector();
		// ����SQL Server���ݿ�
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// ����Դ ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "junlong365";
		try {
			// ��������
			Class.forName(driverName);
			ct = DriverManager.getConnection(dbURL, Name, Pwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("�������ݿ�ɹ�");

			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				// ��ȡ���ݿ� stu�� ������
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));

				// ���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
		} finally {
			// �ر�
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
		//�����û�sql����������
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

	//�õ�����
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	// �õ�ĳ��ĳ�е�����
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

}
