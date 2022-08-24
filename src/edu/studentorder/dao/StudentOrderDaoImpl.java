package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import edu.studentorder.config.Config;
import edu.studentorder.domain.Address;
import edu.studentorder.domain.Adult;
import edu.studentorder.domain.Child;
import edu.studentorder.domain.Person;
import edu.studentorder.domain.StudentOrder;
import edu.studentorder.domain.StudentOrderStatus;
import edu.studentorder.exception.DaoException;

public class StudentOrderDaoImpl implements StudentOrderDao{
	
	public static final String INSERT_ORDER = "INSERT INTO jc_student_order("
			+ "	student_order_status, student_order_date, h_sur_name, h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, h_passport_number,"
			+ " h_issuedate, h_passport_office, h_post_index, h_street_code, h_building, h_extension, h_apartmen, w_sur_name, w_given_name, w_patronymic,"
			+ " w_date_of_birth, w_passport_seria, w_passport_number, w_issuedate, w_passport_office, w_post_index, w_street_code, w_building, w_extension,"
			+ " w_apartmen, sertificate_id, register_office_id, marriage_date)"
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	public static final String INSERT_CHILD = "INSERT INTO jc_student_child("
			+ "	student_order_id, c_sur_name, c_given_name, c_patronymic, c_date_of_birth, c_certificate_number,"
			+ " c_certificate_date, c_register_office_id, c_post_index, c_street_code, c_building, c_extension, c_apartmen)"
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	// TODO refactoring - make one method
		private Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					Config.getProperty(Config.DB_URL),
					Config.getProperty(Config.DB_LOGIN), 
					Config.getProperty(Config.DB_PASSWORD));		
			return con;
	}
		
		@Override
		public long saveStudentOrder(StudentOrder so) throws DaoException {
				long result = -1L;
			try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"})) {
				con.setAutoCommit(false);
				try {
				// Header
				stmt.setInt(1, StudentOrderStatus.START.ordinal());
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				
				// Husband and Wife
				setParamsForAdult(stmt, 3, so.getHusband());
				setParamsForAdult(stmt, 16, so.getWife());
				stmt.setString(29, so.getMarriageSertificateID());
				stmt.setLong(30, so.getMarriageOffice().getOfficeID());
				stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));
				
				stmt.executeUpdate();
			    ResultSet rs = stmt.getGeneratedKeys();
			    if(rs.next()) {
			    	result = rs.getLong(1);
			    }
			    rs.close();
			    
			    saveChildren(con, so, result);
				con.commit();
				}
				catch (SQLException ex) {
					con.rollback();
					throw ex;
				}
			    
			    
			} catch (Exception ex) {
				throw new DaoException(ex);
			}
			
			
			return result;
		}

		private void saveChildren(Connection con, StudentOrder so, long soID) throws SQLException {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
				for(Child child : so.getChildren()) {
				stmt.setLong(1, soID);
				setParamsForChild(stmt, child);
				stmt.executeUpdate();				
				}
			}
		}

		private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException {
				setParamsForPerson(stmt,2,child);
				stmt.setString(6, child.getCertificateNumber());
				stmt.setDate(7, java.sql.Date.valueOf(child.getIssueDate()));
				stmt.setLong(8, child.getIssueDepartmen().getOfficeID());
				setParamsForAdress(stmt,9,child);
		}

		private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
			setParamsForPerson(stmt, start, adult);
			stmt.setString(start+4, adult.getPassportSeria());
			stmt.setString(start+5, adult.getPassportNumber());
			stmt.setDate(start+6, java.sql.Date.valueOf(adult.getIssueDate()));
			stmt.setLong(start+7, adult.getIssueDepartment().getOfficeID());
			setParamsForAdress(stmt, start+8, adult);
		}

		private void setParamsForAdress(PreparedStatement stmt, int start, Person person) throws SQLException {
			Address address = person.getAddress();
			stmt.setString(start, address.getPostCode());
			stmt.setLong(start+1, address.getStreet().getStreetCode());
			stmt.setString(start+2, address.getBuilding());
			stmt.setString(start+3, address.getExtension());
			stmt.setString(start+4, address.getApartmen());
		}

		private void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
			stmt.setString(start, person.getSurName());
			stmt.setString(start+1, person.getGivenName());
			stmt.setString(start+2, person.getPatronymic());
			stmt.setDate(start+3, java.sql.Date.valueOf(person.getDateOfBirth()));
		}
}
