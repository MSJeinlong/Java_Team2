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
 *         这是人事的数据模型类，完成对人事表的各种操作
 *         </p>
 */
public class GradeModel extends AbstractTableModel {

	Vector<String> columns;
	Vector<Vector> rows;
	IGradeService gsim;

	public GradeModel() {
		gsim = new GradeServiceImpl();
		columns = new Vector<String>();
		columns.add("学年");
		columns.add("学期");
		columns.add("课程名称");
		columns.add("总评成绩");
		columns.add("补考成绩");
		columns.add("重修成绩1");
		columns.add("重修成绩2");
		columns.add("重修成绩3");
		columns.add("绩点");
		columns.add("应得学分");

		rows = new Vector();
	}

	// 模糊查询
	public void query(String keyword) {
		List<MyGrade> grades = gsim.fuzzyQuery(keyword);
		this.addRows(grades);
	}
	
	public void critQuery(String name, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.critQuery(name, syear, team, leftVal, rightVal);
		this.addRows(grades);
	}

	// 往表格添加数据的方法
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

			// 把改temp数据添加到rows
			rows.add(temp);
		}
	}

	// 成绩升序
	public void asc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.asc(field, syear, team, leftVal, rightVal);
		//System.out.println(grades.size()); 
		for(MyGrade mg:grades) {
			
		}
		this.addRows(grades);
	}

	// 成绩升序
	public void desc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> grades = gsim.desc(field, syear, team, leftVal, rightVal);
		//System.out.println(grades.size()); 
		this.addRows(grades);
	}

	// 删除
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
