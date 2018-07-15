package com.dao;

import java.util.List;

import com.definiClass.MyGrade;

/**
 * 
 * @author JunLong ����Grade������ݲ������׼
 */
public interface IGradeDAO extends IDAO<String, MyGrade> {
	
	//ΪIGradeDAO�ӿڼ�����������
	
	/**
	 * ����field����(�ɼ�������)
	 * @param field --����field����������
	 * @return	List<MyGrade> -- ��field�Ľ��ͨ������List<MyGrade>����
	 */
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * ����field����(�ɼ�������)
	 * @param field --����field����������fieldΪ���ͣ�����Ϊint,����float
	 * @return	List<MyGrade> -- ��field�Ľ��ͨ������List<MyGrade>����
	 */
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * ��������ѯ
	 * @param name	-- �γ�����
	 * @param syear -- ѧ��
	 * @param team	--ѧ��
	 * @return	--List<MyGrade>�������
	 */
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rightVal);
	
	public float getAvgPoint();
	public float getAvgGrade();
	public int getMaxGrade();
	public int getMinGrade();
	public int getMakeUpCount();
	public int getRetakeCount();
}
