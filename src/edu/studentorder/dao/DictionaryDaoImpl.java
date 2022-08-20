package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.studentorder.config.Config;
import edu.studentorder.domain.PassportOffice;
import edu.studentorder.domain.RegisterOffice;
import edu.studentorder.domain.Street;
import edu.studentorder.exception.DaoException;

public class DictionaryDaoImpl implements DictionaryDao {
	
	private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";
	private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office WHERE p_office_area_id  = ?";
	private static final String GET_REGISTER = "SELECT * FROM jc_register_office WHERE r_office_area_id  = ?";
	

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(Config.getProperty(Config.DB_URL),
				Config.getProperty(Config.DB_LOGIN), 
				Config.getProperty(Config.DB_PASSWORD));		
		return con;
	}
	
	public List<Street> findStreet(String pattern) throws DaoException {
		List<Street> result = new LinkedList<Street>();	
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
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

	@Override
	public List<PassportOffice> findPassportOffices(String areaID) throws DaoException {
		List<PassportOffice> result = new LinkedList<PassportOffice>();	
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {
		stmt.setString(1, areaID);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			PassportOffice str = new PassportOffice(rs.getLong("p_office_id"), rs.getString("p_office_area_id"), rs.getString("p_office_name"));
			result.add(str);
		}
		return result;
		}
		catch (Exception ex) {
			throw new DaoException(ex);
		}		
	}


	@Override
	public List<RegisterOffice> findRegisterOffices(String areaID) throws DaoException {
		List<RegisterOffice> result = new LinkedList<RegisterOffice>();	
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {
		stmt.setString(1, areaID);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			RegisterOffice str = new RegisterOffice(rs.getLong("r_office_id"), rs.getString("r_office_area_id"), rs.getString("r_office_name"));
			result.add(str);
		}
		return result;
		}
		catch (Exception ex) {
			throw new DaoException(ex);
		}
	}
	
	
}
