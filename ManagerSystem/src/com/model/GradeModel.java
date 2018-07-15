package com.model;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.definiClass.MyGrade;
import com.service.IGradeService;
import com.service.impl.GradeServiceImpl;

/**
 * 
 * @author JunLong
 *         <p>
 *         �������µ�����ģ���࣬��ɶ����±�ĸ��ֲ���
 *         </p>
 */
public class GradeModel extends AbstractTableModel {

	Vector<String> columns;
	Vector<Vector> rows;
	IGradeService gsim;

	public GradeModel() {
		gsim = new GradeServiceImpl();
		columns = new Vector<String>();
		columns.add("ѧ��");
		columns.add("ѧ��");
		columns.add("�γ�����");
		columns.add("�����ɼ�");
		columns.add("�����ɼ�");
		columns.add("���޳ɼ�1");
		columns.add("���޳ɼ�2");
		columns.add("���޳ɼ�3");
		columns.add("����");
		columns.add("Ӧ��ѧ��");

		rows = new Vector();
	}

	// ģ����ѯ
	public void query(String keyword) {
		List<MyGrade> grades = gsim.fuzzyQuery(keyword);
		this.addRows(grades);
	}
	
	public void critQuery(String name, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.critQuery(name, syear, team, leftVal, rightVal);
		this.addRows(grades);
	}

	// �����������ݵķ���
	private void addRows(List<MyGrade> grades) {
		for (MyGrade g : grades) {
			Vector temp = new Vector();
			temp.add(g.getSchoolYear());
			temp.add(g.getTeam());
			temp.add(g.getCourseName());
			temp.add(g.getTotalGrade());
			temp.add(g.getMakeUpGrade());
			temp.add(g.getRetakeGrade1());
			temp.add(g.getRetakeGrade2());
			temp.add(g.getRetakeGrade3());
			temp.add(g.getGradePoint());
			temp.add(g.getDueCredits());

			// �Ѹ�temp������ӵ�rows
			rows.add(temp);
		}
	}

	// �ɼ�����
	public void asc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.asc(field, syear, team, leftVal, rightVal);
		//System.out.println(grades.size()); 
		for(MyGrade mg:grades) {
			
		}
		this.addRows(grades);
	}

	// �ɼ�����
	public void desc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.desc(field, syear, team, leftVal, rightVal);
		//System.out.println(grades.size()); 
		this.addRows(grades);
	}

	// ɾ��
	public boolean delete(Set<String> ids) {
		try {
			return gsim.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columns.get(column).toString();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) rows.get(rowIndex)).get(columnIndex);
	}

}
