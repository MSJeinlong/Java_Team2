package com.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.dao.DBTool;
import com.dao.factory.DAOFactory;
import com.definiClass.MyGrade;
import com.service.IGradeService;

public class GradeServiceImpl implements IGradeService {

	private Connection conn;

	@Override
	public boolean insert(MyGrade vo) {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).add(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return false;
	}

	@Override
	public boolean update(MyGrade vo) {
		// TODO Auto-generated method stub
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).update(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return false;
	}

	@Override
	public boolean delete(Set<String> ids) {
		// TODO Auto-generated method stub
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return false;
	}

	@Override
	public List<MyGrade> list() {
		// TODO Auto-generated method stub
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public MyGrade get(String id) {
		// TODO Auto-generated method stub
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public List<MyGrade> fuzzyQuery(String keyword) {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).findAllSplit(keyword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public List<MyGrade> asc(String field, String syear, int team, int leftVal, int rightVal) {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).asc(field, syear, team, leftVal, rightVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public List<MyGrade> desc(String field, String syear, int team, int leftVal, int rightVal) {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).desc(field, syear, team, leftVal, rightVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public List<MyGrade> critQuery(String name, String syear, int team, int leftVal, int rightVal) {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).critQuery(name, syear, team, leftVal, rightVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return null;
	}

	@Override
	public float getAvgPoint() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getAvgPoint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

	@Override
	public float getAvgGrade() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getAvgGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

	@Override
	public int getMaxGrade() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getMaxGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

	@Override
	public int getMinGrade() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getMinGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

	@Override
	public int getMakeUpCount() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getMakeUpCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

	@Override
	public int getRetakeCount() {
		conn = DBTool.open();
		try {
			return DAOFactory.getIGradeDAOInstance(conn).getRetakeCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.close(conn);
		}
		return 0;
	}

}
