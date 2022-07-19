package edu.studentorder;
import java.time.LocalDate;

import edu.studentorder.domain.Address;
import edu.studentorder.domain.Adult;
import edu.studentorder.domain.Child;
import edu.studentorder.domain.StudentOrder;

public class SaveStudentOrder {

	
	
	public static void main(String[] args) {
		//buildStudentOrder();
		
	}

	static long saveStudentOrder(StudentOrder studentOrder) {
		long answer = 199;
		System.out.println("saveStudentOrder:");
		
		return answer;
	}
	
	static StudentOrder buildStudentOrder(long id) {
		StudentOrder so = new StudentOrder();
		so.setStudentOrderID(id);
		so.setMarriageOffice("���� ���������");
		so.setMarriageSertificateID("" + (100000 + id));
		so.setMarriageDate(LocalDate.of(2008, 11, 8));
		
		Address address = new Address("670139", "������", "17", "", "118");
		
		Adult husband = new Adult("������", "��������", "��������", LocalDate.of(1983, 11, 21));
		husband.setPassportNumber("" + (100000 + id));
		husband.setPassportSeria("" + (1000 + id));
		husband.setIssueDate(LocalDate.of(2000, 4, 16));
		husband.setIssueDepartment("���� ���������");
		husband.setStudentID("" + (100 + id));
		husband.setUniversity("����");
		husband.setAddress(address);
		
		Adult wife = new Adult("�������","���������","��������", LocalDate.of(1991, 5, 13));
		wife.setPassportNumber("" + (200000 + id));
		wife.setPassportSeria("" + (2000 + id));
		wife.setIssueDate(LocalDate.of(2001, 5, 19));
		wife.setIssueDepartment("���� ���");
		wife.setStudentID("" + (200 + id));
		wife.setUniversity("�����");
		wife.setAddress(address);
		
		Child child = new Child("������","���","�����������", LocalDate.of(2010, 3, 17));
		child.setCertificateNumber("" + (3000000 + id));
		child.setIssueDate(LocalDate.of(2010, 3, 19));
		child.setIssueDepartmen("���� ���������");
		child.setAddress(address);

		so.setHusband(husband);
		so.setWife(wife);
		so.setChild(child);
		
		return so;
		}
}
