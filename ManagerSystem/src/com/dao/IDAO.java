package com.dao;

import java.util.List;
import java.util.Set;

/**
 * �������е����ݱ�Ӧ�þ߱��л���CRUD���ܣ����ӡ��޸�ȫ����ɾ�����ݡ�����id��ʾ������ͳ�ƣ�����ô��Щ���ܵķ���ÿ���ӿڶ�Ҫ�ظ����塣
 * ������DAO�ӿڶ���Ĺ����У���ͬ�ı��������ڣ�VO�ࡢ�������͡�Ϊ�˱�������ظ�������һ�������Ľӿڡ�
 * ��ôΪ�˽���ظ����⣬ʹ�÷���ʵ�ֽӿڵļ̳в�����
 * 
 * ���幫����DAO�����ӿڱ�׼�������Ĺ��ܰ��������ӡ��޸�ȫ����ɾ�����ݡ����ݱ�Ų�ѯ����ѯȫ������ҳ��ʾ������ͳ��
 * 
 * 
 * @param <K>
 *            ��ʾҪ�������������ͣ����ӽӿ�ʵ��
 * @param <V>
 *            ��ʾҪ������VO���ͣ����ӽӿ�ʵ��
 */
@SuppressWarnings("unused")
public interface IDAO<K, V> {
	/**
	 * ʵ�����ݵ����Ӳ���
	 * 
	 * @param vo
	 *            ������Ҫ�������ݵ�VO����
	 * @return ���ݱ���ɹ�����true�����򷵻�false
	 * @throws Exception
	 *             SQLִ���쳣
	 */
	public boolean add(V vo) throws Exception;

	/**
	 * ʵ�����ݵ��޸Ĳ���
	 * 
	 * @param vo
	 *            ������Ҫ�޸����ݵ���Ϣ��һ��Ҫ�ṩ��ID����
	 * @return ���ݱ���ɹ�����true�����򷵻�false
	 * @throws Exception
	 *             SQLִ���쳣
	 */
	public boolean update(V vo) throws Exception;

	/**
	 * ִ�����ݵ�����ɾ������������Ҫɾ����������Set���ϵ���ʽ����
	 * ɾ���ɹ�����true��ɾ�������ݸ�����Ҫɾ�������ݸ�����ͬ������Ҫɾ��4��������Ҫ�����ɾ��5�����򷵻�false�������򷵻�false
	 * 
	 * @param ids
	 *            ����������Ҫɾ��������ID�����������ظ�����
	 * @return ɾ���ɹ�����true��ɾ�������ݸ�����Ҫɾ�������ݸ�����ͬ�������򷵻�false
	 * @throws Exception
	 *             SQLִ���쳣
	 */
	public boolean delete(Set<K> ids) throws Exception;

	/**
	 * ����ID��ѯ��Ϣ
	 * 
	 * @param id
	 *            Ҫ��ѯ�Ŀγ�����
	 * @return �����Ա��Ϣ���ڣ���������VO��������ʽ���أ������Ա���ݲ����ڣ��򷵻�null
	 * @throws Exception
	 *             SQLִ���쳣
	 */
	public V findById(K id) throws Exception;

	/**
	 * ��ѯָ�����ݱ��ȫ����¼�������Լ��ϵ���ʽ����
	 * 
	 * @return ������������ݣ������е����ݻ��װΪVO�����������List���Ϸ��أ� ���û�����ݣ���ô���ϵĳ���Ϊ0��size����==
	 *         0������null��
	 * @throws Exception
	 *             SQLִ���쳣
	 */
	public List<V> findAll() throws Exception;

	/**
	 * ��ҳ�������ݵ�ģ����ѯ����ѯ����Լ��ϵ���ʽ����
	 * 
	 * @param ���ݹؼ���keyword����ģ����ѯ,��ƥ��
	 *            => keyword% �����м�¼�������ǲ�ѯ���õĽ��
	 * @return ������������ݣ������е����ݻ��װΪVO�����������List���Ϸ��أ� ���û�����ݣ���ô���ϵĳ���Ϊ0��size����==
	 *         0������null��
	 * @throws Exception
	 *             SQLִ���쳣
	 */

	public List<V> findAllSplit(K keyword) throws Exception;

	/**
	 * ����ģ����ѯ��������ͳ�ƣ��������û�м�¼ͳ�ƵĽ������0
	 * 
	 * @param column
	 *            Ҫ����ģ����ѯ��������
	 * @param keyWord
	 *            ģ����ѯ�Ĺؼ���
	 * @return ���ر��е������������û�����ݷ���0
	 * @throws Exception
	 */
	// public Integer getAllCount(String column, String keyWord) throws Exception ;

}