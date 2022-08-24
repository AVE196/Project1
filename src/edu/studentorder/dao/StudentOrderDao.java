package edu.studentorder.dao;

import edu.studentorder.domain.StudentOrder;
import edu.studentorder.exception.DaoException;

public interface StudentOrderDao {
	long saveStudentOrder(StudentOrder so) throws DaoException;
}
