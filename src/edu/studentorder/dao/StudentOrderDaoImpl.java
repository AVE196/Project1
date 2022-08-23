package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import edu.studentorder.config.Config;
import edu.studentorder.domain.Address;
import edu.studentorder.domain.Adult;
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
		public long SaveStudentOrder(StudentOrder so) throws DaoException {
				long result = -1L;
			try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"})) {
				
				// Header
				stmt.setInt(1, StudentOrderStatus.START.ordinal());
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				
				// Husband and Wife
				CreateParamForAdult(stmt, 3, so.getHusband());
				CreateParamForAdult(stmt, 16, so.getWife());
				stmt.setString(29, so.getMarriageSertificateID());
				stmt.setLong(30, so.getMarriageOffice().getOfficeID());
				stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));
				
				stmt.executeUpdate();
			    ResultSet rs = stmt.getGeneratedKeys();
			    if(rs.next()) {
			    	result = rs.getLong(1);
			    }
			    rs.close();
				
			} catch (Exception ex) {
				throw new DaoException(ex);
			}
			
			
			return result;
		}

		private void CreateParamForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
			stmt.setString(start, adult.getSurName());
			stmt.setString(start+1, adult.getGivenName());
			stmt.setString(start+2, adult.getPatronymic());
			stmt.setDate(start+3, java.sql.Date.valueOf(adult.getDateOfBirth()));
			stmt.setString(start+4, adult.getPassportSeria());
			stmt.setString(start+5, adult.getPassportNumber());
			stmt.setDate(start+6, java.sql.Date.valueOf(adult.getIssueDate()));
			stmt.setLong(start+7, adult.getIssueDepartment().getOfficeID());
			Address addressH = adult.getAddress();
			stmt.setString(start+8, addressH.getPostCode());
			stmt.setLong(start+9, addressH.getStreet().getStreetCode());
			stmt.setString(start+10, addressH.getBuilding());
			stmt.setString(start+11, addressH.getExtension());
			stmt.setString(start+12, addressH.getApartmen());
		}
}
