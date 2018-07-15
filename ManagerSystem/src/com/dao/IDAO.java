package com.dao;

import java.util.List;
import java.util.Set;

/**
 * 几乎所有的数据表都应该具备有基础CRUD功能（增加、修改全部、删除数据、根据id显示、数据统计），那么这些功能的方法每个接口都要重复定义。
 * 在整个DAO接口定义的过程中，不同的表区别在于：VO类、主键类型。为了避免代码重复，定义一个公共的接口。
 * 那么为了解决重复问题，使用泛型实现接口的继承操作。
 * 
 * 定义公共的DAO操作接口标准，基本的功能包括：增加、修改全部、删除数据、根据编号查询、查询全部、分页显示、数据统计
 * 
 * 
 * @param <K>
 *            表示要操作的主键类型，由子接口实现
 * @param <V>
 *            表示要操作的VO类型，由子接口实现
 */
@SuppressWarnings("unused")
public interface IDAO<K, V> {
	/**
	 * 实现数据的增加操作
	 * 
	 * @param vo
	 *            包含了要增加数据的VO对象
	 * @return 数据保存成功返回true，否则返回false
	 * @throws Exception
	 *             SQL执行异常
	 */
	public boolean add(V vo) throws Exception;

	/**
	 * 实现数据的修改操作
	 * 
	 * @param vo
	 *            包含了要修改数据的信息，一定要提供有ID内容
	 * @return 数据保存成功返回true，否则返回false
	 * @throws Exception
	 *             SQL执行异常
	 */
	public boolean update(V vo) throws Exception;

	/**
	 * 执行数据的批量删除操作，所有要删除的数据已Set集合的形式保存
	 * 删除成功返回true（删除的数据个数与要删除的数据个数相同，例如要删除4个，可是要求的是删除5个，则返回false），否则返回false
	 * 
	 * @param ids
	 *            包含了所有要删除的数据ID，不包含有重复内容
	 * @return 删除成功返回true（删除的数据个数与要删除的数据个数相同），否则返回false
	 * @throws Exception
	 *             SQL执行异常
	 */
	public boolean delete(Set<K> ids) throws Exception;

	/**
	 * 根据ID查询信息
	 * 
	 * @param id
	 *            要查询的课程名称
	 * @return 如果雇员信息存在，则将数据以VO类对象的形式返回，如果雇员数据不存在，则返回null
	 * @throws Exception
	 *             SQL执行异常
	 */
	public V findById(K id) throws Exception;

	/**
	 * 查询指定数据表的全部记录，并且以集合的形式返回
	 * 
	 * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用List集合返回， 如果没有数据，那么集合的长度为0（size（）==
	 *         0，不是null）
	 * @throws Exception
	 *             SQL执行异常
	 */
	public List<V> findAll() throws Exception;

	/**
	 * 分页进行数据的模糊查询，查询结果以集合的形式返回
	 * 
	 * @param 根据关键字keyword进行模糊查询,即匹配
	 *            => keyword% 的所有记录都将会是查询所得的结果
	 * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用List集合返回， 如果没有数据，那么集合的长度为0（size（）==
	 *         0，不是null）
	 * @throws Exception
	 *             SQL执行异常
	 */

	public List<V> findAllSplit(K keyword) throws Exception;

	/**
	 * 进行模糊查询数据量的统计，如果表中没有记录统计的结果就是0
	 * 
	 * @param column
	 *            要进行模糊查询的数据列
	 * @param keyWord
	 *            模糊查询的关键字
	 * @return 返回表中的数据量，如果没有数据返回0
	 * @throws Exception
	 */
	// public Integer getAllCount(String column, String keyWord) throws Exception ;

}