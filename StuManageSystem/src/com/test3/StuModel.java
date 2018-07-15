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
 * �����ҵ�һ��stu���ģ��
 * ���԰Ѷ�student��ĸ��ֲ�����װ��������
 */
public class StuModel extends AbstractTableModel {

	// rowData�������������, columnNames�������
	Vector rowData, columnNames;



	//���캯��,���ڳ�ʼ�����ǵ�����ģ��
	public StuModel(String sql){
		//SQLHelper sqlhelpr	
	}
	
	public StuModel(){
		
	}
	
	//���ѧ��(����ɾ����)
	public static boolean updateStu(String sql, String[] paras) {
		boolean b = true;
		
		//����SQLHepler(������򲢷��Բ����ǣ����԰�SQLHelper����static)
		SQLHelper sqlhelper = new SQLHelper();		
		return sqlhelper.updExecute(sql, paras);
	}
	
	//��ѯ���ʾ��ǳ�ʼ��
	public void queryStu(String sql, String []paras) {
		
		SQLHelper sqlhelper = null;
		
/*		if(sql.equals("")) {
			sql = "select * from student";
		}*/
		
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
			sqlhelper.close();
		}

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
