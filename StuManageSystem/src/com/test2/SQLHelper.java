package com.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author JunLong ����һ�������ݿ���в������ࣨSqlHelper��
 */
public class SQLHelper {
	// ����������ݿ���Ҫ�ı���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
	String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// ����Դ ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
	String UserName = "sa";
	String Passwd = "junlong365";

	// ��ѯ���ݿ�Ĳ���
	public ResultSet queryExecute(String sql, String[] paras) {
		try {

			// �������ݿ�
			this.ConnectDatabase(sql, paras);

			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// �ر���Դ
		}
		return rs;
	}

	// �޲β�ѯ

	public ResultSet queryExecute(String sql) {
		try {

			Class.forName(driverName);
			// 2���õ�����
			ct = DriverManager.getConnection(dbURL, UserName, Passwd);
			// 3������ps
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// �ر���Դ
		}
		return rs;
	}

	// ���ѧ��(����ɾ����)
	public boolean updExecute(String sql, String[] paras) {
		boolean b = true;
		try {
			// �������ݿ�
			this.ConnectDatabase(sql, paras);
			// ִ�в���
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

	// �������ݿ���Դ
	public void ConnectDatabase(String sql, String[] paras) {
		// 1����������
		try {
			Class.forName(driverName);
			// 2���õ�����
			ct = DriverManager.getConnection(dbURL, UserName, Passwd);
			// 3������ps
			ps = ct.prepareStatement(sql);
			// �� ps ����( ? )��ֵ
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �ر����ݿ���Դ
	public void close() {
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
