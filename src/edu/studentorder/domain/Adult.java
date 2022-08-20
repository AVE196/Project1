package edu.studentorder.domain;
import java.time.LocalDate;

public class Adult extends Person {

	public Adult(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
		super(surName, givenName, patronymic, dateOfBirth);
	}
	
	public Adult() {		
	}
	
	private String passportSeria;
	private String passportNumber;
	private LocalDate issueDate;
	private PassportOffice issueDepartment;
	private String university;
	private String studentID;
	private Address address;
		
	public String getPassportSeria() {
		return passportSeria;
	}
	public void setPassportSeria(String passportSeria) {
		this.passportSeria = passportSeria;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate string) {
		this.issueDate = string;
	}

	public PassportOffice getIssueDepartment() {
		return issueDepartment;
	}

	public void setIssueDepartment(PassportOffice issueDepartment) {
		this.issueDepartment = issueDepartment;
	}

	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
			
}
