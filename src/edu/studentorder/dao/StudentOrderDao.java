package edu.studentorder.dao;

import java.util.List;

import edu.studentorder.domain.StudentOrder;
import edu.studentorder.exception.DaoException;

public interface StudentOrderDao {
	long saveStudentOrder(StudentOrder so) throws DaoException;
	List<StudentOrder> getStudentOrdersTwoSelect() throws DaoException;
	List<StudentOrder> getStudentOrdersOneSelect() throws DaoException;
}
