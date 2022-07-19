package edu.studentorder.domain;

import java.time.LocalDate;

public class StudentOrder {

	
	private long studentOrderID;
	private Adult husband;
	private Adult wife;
	private Child child;
	private String MarriageSertificateID;
	private String MarriageOffice;
	private LocalDate MarriageDate;
	
	public long getStudentOrderID() {
		return studentOrderID;
	}
	public void setStudentOrderID(long studentOrderID) {
		this.studentOrderID = studentOrderID;
	}
	public Adult getHusband() {
		return husband;
	}
	public void setHusband(Adult husband) {
		this.husband = husband;
	}
	public Adult getWife() {
		return wife;
	}
	public void setWife(Adult wife) {
		this.wife = wife;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public String getMarriageSertificateID() {
		return MarriageSertificateID;
	}
	public void setMarriageSertificateID(String marriageSertificateID) {
		MarriageSertificateID = marriageSertificateID;
	}
	public String getMarriageOffice() {
		return MarriageOffice;
	}
	public void setMarriageOffice(String marriageOffice) {
		MarriageOffice = marriageOffice;
	}
	public LocalDate getMarriageDate() {
		return MarriageDate;
	}
	public void setMarriageDate(LocalDate marriageDate) {
		MarriageDate = marriageDate;
	}
	
	
	
}
