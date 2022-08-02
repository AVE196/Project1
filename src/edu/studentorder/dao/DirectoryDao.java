package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.studentorder.domain.Street;

public class DirectoryDao {

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432",
				"postgres",
				"pfqrfvjz"
				);		
		return con;
	}
	
	public List<Street> findStreet(String pattern) throws ClassNotFoundException, SQLException {
		List<Street> result = new LinkedList();
		String sql = "SELECT street_code, street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%" + pattern + "%')";
		Statement stm = getConnection().createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
			result.add(str);
		}
		
		return result;
	}
	
	
}
