package com.service;

import java.util.List;

import com.definiClass.MyGrade;

public interface IGradeService extends IService<String, MyGrade> {
	/**
	 * 升序方法
	 * @param field -- 排序参数
	 * @return	-- 返回排序后的结果
	 */
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * 降序方法
	 * @param field -- 排序参数
	 * @return	-- 返回排序后的结果
	 */
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal);
	
	/**
	 * 多条件查询
	 * @param name	-- 课程名称
	 * @param syear -- 学年
	 * @param team	--学期
	 * @return	--List<MyGrade>结果集合
	 */
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rigthVal);
	
	public float getAvgPoint();
	public float getAvgGrade();
	public int getMaxGrade();
	public int getMinGrade();
	public int getMakeUpCount();
	public int getRetakeCount();
}
