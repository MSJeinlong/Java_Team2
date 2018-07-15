package dao;

import java.sql.*;

import annotation1.Ann1;

public class TestDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	// 2.创建statement类对象，用来执行SQL语句！！
		Connection conn = null;
		Statement statement = null;
		conn = DBTools.open();
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 要执行的SQL语句
		String sql = "select * from student";
		// 3.ResultSet类，用来存放获取的结果集！！
		String name;
		int id, age;
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt(1);
				name = rs.getString("name");
				age = rs.getInt("age");
				System.out.println("id:"+id+", name:"+name+", age:"+age);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBTools.close(conn);
		}*/
		UserDaoImplement dao = new UserDaoImplement();
		//User u = new User("wangwu", 30);
		//dao.add(u);
		User u = dao.getById(5);
		//u.setName("huang");
		//dao.update(u);
		//u = dao.getById(3);
		//u.setName("王大锤");
		//dao.update(u);
		dao.delete(4);
		dao.find();
		Ann1 ann1 = new Ann1();
		System.out.println(ann1);
	}

}
