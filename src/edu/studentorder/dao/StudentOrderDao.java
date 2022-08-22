package edu.studentorder.dao;

import edu.studentorder.domain.StudentOrder;
import edu.studentorder.exception.DaoException;

public interface StudentOrderDao {
	long SaveStudentOrder(StudentOrder so) throws DaoException;
}
