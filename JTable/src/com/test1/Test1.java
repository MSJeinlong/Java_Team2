package com.test1;
/**
 * JTable��ʹ��
 * 
 *
 */
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
public class Test1 extends JFrame{

	//rowData�������������, columnNames�������
	Vector rowData,  columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
	}
	
	//���췽��
	public Test1() {
		columnNames = new Vector();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData = new Vector();
		//rowData���Դ�Ŷ���
		Vector hang = new Vector();
		hang.add("sp001");
		hang.add("�����");
		hang.add("��");
		hang.add("500");
		hang.add("����ɽ");
		hang.add("������");
		
		//���뵽rowData
		rowData.add(hang);
		
		//��ʼ��JTable
		jt = new JTable(rowData, columnNames);
		
		//��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jt);
		
		//��jsp�ŵ�jframe
		this.add(jsp);
		
		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
