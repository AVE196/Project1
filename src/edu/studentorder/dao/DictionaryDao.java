package edu.studentorder.dao;

import java.util.List;

import edu.studentorder.domain.Street;
import edu.studentorder.exception.DaoException;

public interface DictionaryDao {
	
	List<Street> findStreet(String pattern) throws DaoException;
	
}
