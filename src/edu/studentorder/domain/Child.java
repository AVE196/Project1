package edu.studentorder.domain;
import java.time.LocalDate;

public class Child extends Person{

	public Child(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
		super(surName, givenName, patronymic, dateOfBirth);
	}
	
	private String certificateNumber;
	private LocalDate issueDate;
	private String issueDepartmen;
	
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueDepartmen() {
		return issueDepartmen;
	}
	public void setIssueDepartmen(String issueDepartmen) {
		this.issueDepartmen = issueDepartmen;
	}
	
	
}
