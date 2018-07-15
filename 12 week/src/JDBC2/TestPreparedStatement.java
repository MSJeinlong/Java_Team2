package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPreparedStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insert("张艺兴", 27);
		User user = new User();
		user.setName("王大锤");
		//user.setAge(30);
		//insert(user);
		update(user, 5);
		//
	}
	
	//带参数的insert方法，更加灵活
	private static void insert(String name, int age) {
		Connection conn = DBTool.openDatabase();
		String sql = "insert into user(name,age) value(?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//带参数的insert方法，更加灵活
	private static void insert(User user) {
		Connection conn = DBTool.openDatabase();
		String sql = "insert into user(name,age) value(?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//带参数的update方法，更加灵活
	private static void update(User user, int id) {
		Connection conn = DBTool.openDatabase();
		String sql = "update user set name = ? where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static User query(int id) {
		Connection conn = DBTool.openDatabase();
		String sql = "select * from user where id = ?";
		User p = new User();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
/*			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.executeUpdate();*/
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int ID= rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				p.setId(ID);
				p.setName(name);
				p.setAge(age);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
}
