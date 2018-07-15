package com.service;

import java.util.List;
import java.util.Set;

import com.definiClass.MyGrade;

/**
 * 
 * @author JunLong
 *
 * @param <K>
 *            Ҫ�������������ͣ����ӽӿ�ʵ��
 * @param <V>
 *            Ҫ������VO���ͣ����ӽӿ�ʵ��
 */
public interface IService<K, V> {
	public boolean insert(V vo) throws Exception;

	public boolean update(V vo) throws Exception;

	public boolean delete(Set<K> ids) throws Exception;

	public List<MyGrade> list() throws Exception;

	public MyGrade get(K id) throws Exception;

	public List<MyGrade> fuzzyQuery(String keyword);
}
