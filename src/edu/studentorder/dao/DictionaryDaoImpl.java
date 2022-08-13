package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.studentorder.domain.Street;
import edu.studentorder.exception.DaoException;

public class DictionaryDaoImpl implements DictionaryDao {
	
	private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";


	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/jc_student", "postgres", "pfqrfvjz");		
		return con;
	}
	
	public List<Street> findStreet(String pattern) throws DaoException {
		
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
		List<Street> result = new LinkedList();	
		stmt.setString(1, "%" + pattern + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
			result.add(str);
		}
		return result;
		}
		catch (Exception ex) {
			throw new DaoException(ex);
		}
		
		
	}
	
	
}
