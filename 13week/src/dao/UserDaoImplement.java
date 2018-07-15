package dao;

//3:实现dao接口
import java.sql.*;
import java.util.*;

public class UserDaoImplement implements UserDao {

	@Override
	public void add(User u) {
		// TODO Auto-generated method stub
		Connection conn = DBTools.open();
		
		String sql = "insert into student(name, age) values(?, ?);";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(3, u.getId());
			pstmt.setString(1, u.getName());
			pstmt.setInt(2, u.getAge());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTools.close(conn);
		}

	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		Connection conn = DBTools.open();
		String sql = "update student set name = ?,age = ? where id = ?";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(3, u.getId());
			pstmt.setString(1, u.getName());
			pstmt.setInt(2, u.getAge());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTools.close(conn);
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection conn = DBTools.open();
		String sql = "delete from student where id = ?";
		PreparedStatement pstmt= null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			// pstmt.setString(1, u.getName());
			// pstmt.setInt(2, u.getAge());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTools.close(conn);
		}
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		User u = new User();
		Connection conn = DBTools.open();
		String sql = "select * from student where id = ?";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			// pstmt.setString(1, u.getName());
			// pstmt.setInt(2, u.getAge());
			// pstmt.executeUpdate(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// int Id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt(3);
				u.setId(id);
				u.setAge(age);
				u.setName(name);
			}
			System.out.println(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTools.close(conn);
		}
		return u;
	}

	@Override
	public List<User> find() {
		// TODO Auto-generated method stub
		Connection conn = DBTools.open();
		String sql = "select * from student";
		List<User> list = new ArrayList<User>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User u = new User();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				u.setAge(age);
				u.setId(id);
				u.setName(name);
				list.add(u);
			}
			System.out.println(list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTools.close(conn);
		}
		return list;
	}

}
