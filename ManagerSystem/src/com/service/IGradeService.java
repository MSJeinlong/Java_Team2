package com.service;

import java.util.List;

import com.definiClass.MyGrade;

public interface IGradeService extends IService<String, MyGrade> {
	/**
	 * ���򷽷�
	 * @param field -- �������
	 * @return	-- ���������Ľ��
	 */
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * ���򷽷�
	 * @param field -- �������
	 * @return	-- ���������Ľ��
	 */
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * ��������ѯ
	 * @param name	-- �γ�����
	 * @param syear -- ѧ��
	 * @param team	--ѧ��
	 * @return	--List<MyGrade>�������
	 */
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rigthVal);
	
	public float getAvgPoint();
	public float getAvgGrade();
	public int getMaxGrade();
	public int getMinGrade();
	public int getMakeUpCount();
	public int getRetakeCount();
}
