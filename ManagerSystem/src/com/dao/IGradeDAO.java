package com.dao;

import java.util.List;

import com.definiClass.MyGrade;

/**
 * 
 * @author JunLong 定义Grade表的数据层操作标准
 */
public interface IGradeDAO extends IDAO<String, MyGrade> {
	
	//为IGradeDAO接口加入其他功能
	
	/**
	 * 根据field升序(成绩、绩点)
	 * @param field --根据field来进行升序
	 * @return	List<MyGrade> -- 将field的结果通过类型List<MyGrade>返回
	 */
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * 根据field降序(成绩、绩点)
	 * @param field --根据field来进行升序，field为泛型，可能为int,或者float
	 * @return	List<MyGrade> -- 将field的结果通过类型List<MyGrade>返回
	 */
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * 多条件查询
	 * @param name	-- 课程名称
	 * @param syear -- 学年
	 * @param team	--学期
	 * @return	--List<MyGrade>结果集合
	 */
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rightVal);
	
	public float getAvgPoint();
	public float getAvgGrade();
	public int getMaxGrade();
	public int getMinGrade();
	public int getMakeUpCount();
	public int getRetakeCount();
}
