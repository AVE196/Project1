package edu.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.studentorder.config.Config;
import edu.studentorder.domain.StudentOrder;
import edu.studentorder.domain.StudentOrderStatus;
import edu.studentorder.exception.DaoException;

public class StudentOrderDaoImpl implements StudentOrderDao{
	
	public static final String SET_SO = "INSERT INTO jc_student_order("
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

			try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(SET_SO)) {
				
				stmt.setInt(1, StudentOrderStatus.START.ordinal());
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(so.getStudentOrderDate()));
				stmt.setString(3, so.getHusband().getSurName());
				stmt.setString(4, so.getHusband().getGivenName());
				stmt.setString(5, so.getHusband().getPatronymic());
				stmt.setDate(6, java.sql.Date.valueOf(so.getHusband().getDateOfBirth()));
				stmt.setString(7, so.getHusband().getPassportSeria());
				stmt.setString(8, so.getHusband().getPassportNumber());
				stmt.setDate(9, java.sql.Date.valueOf(so.getHusband().getIssueDate()));
				
				
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			return 0L;
		}
}
