package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.dao.IGradeDAO;
import com.definiClass.MyGrade;

public class GradeDAOImpl implements IGradeDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	// 构造方法
	public GradeDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean add(MyGrade vo) throws Exception {
		String sql = "insert into grade(id, name, schoolYear, team,courseName, totalGrade, makeUpGrade, retakeGrade1, retakeGrade2, retakeGrade3, gradepoint, dueCredits)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		this.setPstmt(vo);
		return pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean update(MyGrade vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update grade set id = ?, name = ?, schoolYear = ?, team = ?, courseName = ?, totalGrade = ?, makeUpGrade = ?, "
				+ "retakeGrade1 = ?, retakeGrade2 = ?, retakeGrade3 = ?, gradePoint = ?, dueCredits = ? where cno = ?";
		pstmt = conn.prepareStatement(sql);
		this.setPstmt(vo);
		pstmt.setInt(13, vo.getCno());
		// System.out.println(sql);
		return pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Set<String> ids) throws SQLException {
		if (ids == null || ids.size() == 0) { // 没有要删除的数据
			return false;
		}
		// 字符串的拼凑使用StringBuffer
		StringBuffer sql = new StringBuffer();
		sql.append("delete from grade where courseName in(");
		Iterator<String> iter = ids.iterator();
		while (iter.hasNext()) {
			// 删除语句的格式是delete from grade where cno in ("","","",);
			sql.append(iter.next()).append(",");
		}
		// 删除最后一个逗号，并且添加)
		sql.delete(sql.length() - 1, sql.length()).append(")");
		/*
		 * conn.prepareStatement()只接受String，所以让 StringBuffer转为String，需要用toString(),
		 * 这是重点强调了的知识点
		 * 
		 */
		// System.out.println(sql.toString());
		this.pstmt = this.conn.prepareStatement(sql.toString());
		// pstmt.executeUpdate()返回结果为int
		return this.pstmt.executeUpdate() == ids.size();
	}

	/**
	 * 这里id不是grade表的id(grade的id即cno，是自动增长的，不方便查询)
	 * 而是grade表的courseName,即课程名，按课程名可以进行模糊查询
	 */
	@Override
	public MyGrade findById(String id) throws Exception {
		// TODO Auto-generated method stub
		// MyGrade vo = null;
		String sql = "select *  from grade where courseName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return setGrade(rs);
		}
		return null;
	}

	@Override
	public List<MyGrade> findAll() throws Exception {
		List<MyGrade> all = new ArrayList<MyGrade>();
		String sql = "select * from grade";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			all.add(setGrade(rs));
		}
		return all;
	}

	@Override
	public List<MyGrade> findAllSplit(String keyword) throws Exception {
		// TODO Auto-generated method stub
		List<MyGrade> res = new ArrayList<MyGrade>();
		String sql = "select *  from grade where courseName like ?";
		keyword = "%" + keyword + "%";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			res.add(setGrade(rs));
		}
		return res;
	}

	private void setPstmt(MyGrade vo) {
		try {
			pstmt.setInt(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getSchoolYear());
			pstmt.setInt(4, vo.getTeam());
			pstmt.setString(5, vo.getCourseName());
			if (vo.getTotalGrade() != null) {
				pstmt.setInt(6, vo.getTotalGrade());
			} else {
				pstmt.setString(6, null);
			}
			if (vo.getMakeUpGrade() != null) {
				pstmt.setInt(7, vo.getMakeUpGrade());
			} else {
				pstmt.setString(7, null);
			}
			if (vo.getRetakeGrade1() != null) {
				pstmt.setInt(8, vo.getRetakeGrade1());
			} else {
				pstmt.setString(8, null);
			}
			if (vo.getRetakeGrade2() != null) {
				pstmt.setInt(9, vo.getRetakeGrade2());
			} else {
				pstmt.setString(9, null);
			}
			if (vo.getRetakeGrade3() != null) {
				pstmt.setInt(10, vo.getRetakeGrade3());
			} else {
				pstmt.setString(10, null);
			}
			if (vo.getGradePoint() != null) {
				pstmt.setFloat(11, vo.getGradePoint());
			} else {
				pstmt.setString(11, null);
			}
			if (vo.getDueCredits() != null) {
				pstmt.setFloat(12, vo.getDueCredits());
			} else {
				pstmt.setString(12, null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MyGrade setGrade(ResultSet rs) {
		MyGrade vo = null;
		try {

			vo = new MyGrade();
			vo.setCno(rs.getInt(1));
			vo.setId(rs.getInt(2));
			vo.setName(rs.getString(3));
			vo.setSchoolYear(rs.getString(4));
			vo.setTeam(rs.getInt(5));
			vo.setCourseName(rs.getString(6));
			int val = rs.getInt(7);
			// 判断读取的值是否为空
			if (rs.wasNull()) {
				vo.setTotalGrade(null);
			} else {
				vo.setTotalGrade(val);
			}
			val = rs.getInt(8);
			if (rs.wasNull()) {
				vo.setMakeUpGrade(null);
			} else {
				vo.setMakeUpGrade(val);
			}
			val = rs.getInt(9);
			if (rs.wasNull()) {
				vo.setRetakeGrade1(null);
			} else {
				vo.setRetakeGrade1(val);
			}
			val = rs.getInt(10);
			if (rs.wasNull()) {
				vo.setRetakeGrade2(null);
			} else {
				vo.setRetakeGrade2(val);
			}
			val = rs.getInt(11);
			if (rs.wasNull()) {
				vo.setRetakeGrade3(null);
			} else {
				vo.setRetakeGrade3(val);
			}
			float val2 = rs.getFloat(12);
			if (rs.wasNull()) {
				vo.setGradePoint(null);
			} else {
				vo.setGradePoint(val2);
			}
			val2 = rs.getFloat(13);
			if (rs.wasNull()) {
				vo.setDueCredits(null);
			} else {
				vo.setDueCredits(val2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> all = new ArrayList<MyGrade>();
		String sql = "select * from grade where "+field+" is not NULL and schoolYear like ?";
		int paras = 1;
		if(team != 0) {
			sql += " and team = ?";
		}
		if(leftVal > 0 && rightVal > 0) {
			sql += " and totalGrade >= ? and totalGrade <= ?";
		}
		sql += " order by " + field + " asc";
		syear = "%" + syear + "%";
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(paras++, syear);
			if(team != 0) {
				pstmt.setInt(paras++, team);
			}
			if(leftVal > 0 && rightVal > 0) {
				pstmt.setInt(paras++, leftVal);
				pstmt.setInt(paras, rightVal);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				all.add(setGrade(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}

	@Override
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal) {
		List<MyGrade> all = new ArrayList<MyGrade>();
		String sql = "select * from grade where "+field+" is not NULL and schoolYear like ?";		
		int paras = 1;
		if(team != 0) {
			sql += " and team = ?";
		}
		if(leftVal > 0 && rightVal > 0) {
			sql += " and totalGrade >= ? and totalGrade <= ?";
		}
		sql += " order by " + field + " desc";
		syear = "%" + syear + "%";
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(paras++, syear);
			if(team != 0) {
				pstmt.setInt(paras++, team);
			}
			if(leftVal > 0 && rightVal > 0) {
				pstmt.setInt(paras++, leftVal);
				pstmt.setInt(paras, rightVal);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				all.add(setGrade(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}

	@Override
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rightVal) {
		// TODO Auto-generated method stub
		List<MyGrade> res = new ArrayList<MyGrade>();
		int paras = 1;
		String sql = "select *  from grade where courseName like ? and schoolYear like ?";
		if(team != 0) {
			sql += " and team = ?";
		}
		
		if(!(leftVal < 0 || rightVal < 0)) {
			sql += " and totalGrade >= ? and totalGrade <= ?";
		}
		name = "%" + name + "%";
		syear = "%" + syear + "%";
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(paras++, name);
			pstmt.setString(paras++, syear);
			if(team != 0) {
				pstmt.setInt(paras++, team);
			}
			if(!(leftVal < 0 || rightVal < 0)) {
				pstmt.setInt(paras++, leftVal);
				pstmt.setInt(paras, rightVal);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				res.add(setGrade(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public float getAvgPoint() {
		String sql = "select AVG(gradePoint) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public float getAvgGrade() {
		String sql = "select AVG(totalGrade) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}

	@Override
	public int getMaxGrade() {
		String sql = "select MAX(totalGrade) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

	@Override
	public int getMinGrade() {
		String sql = "select MIN(totalGrade) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

	@Override
	public int getMakeUpCount() {
		String sql = "select count(makeUpGrade) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

	@Override
	public int getRetakeCount() {
		String sql = "select count(retakeGrade1) from grade";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

}
