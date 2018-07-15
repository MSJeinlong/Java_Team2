package com.dao.factory;

import java.sql.Connection;

import com.dao.IGradeDAO;
import com.dao.impl.GradeDAOImpl;

/**
 * 
 * @author JunLong ���������
 */
public class DAOFactory {

	public static IGradeDAO getIGradeDAOInstance(Connection conn) {
		return new GradeDAOImpl(conn);
	}
}
