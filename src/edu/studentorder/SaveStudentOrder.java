package edu.studentorder;
import java.time.LocalDate;
import java.util.List;

import edu.studentorder.dao.DictionaryDaoImpl;
import edu.studentorder.dao.StudentOrderDaoImpl;
import edu.studentorder.domain.Address;
import edu.studentorder.domain.Adult;
import edu.studentorder.domain.Child;
import edu.studentorder.domain.PassportOffice;
import edu.studentorder.domain.RegisterOffice;
import edu.studentorder.domain.Street;
import edu.studentorder.domain.StudentOrder;
import edu.studentorder.domain.University;

public class SaveStudentOrder {

	
	
	public static void main(String[] args) throws Exception{
		/*	
		List<Street> streets = new DictionaryDaoImpl().findStreet("про");
		for (Street str: streets) {
			System.out.println(str.getStreetName());
		}
		List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
		for (PassportOffice p : po) {
			System.out.println(p.getOfficeName());
		}
		List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
		for (RegisterOffice r : ro) {
			System.out.println(r.getOfficeName());
		}

		List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
		for (CountryArea c : ca1) {
			System.out.println(c.getAreaId() + ":" + c.getAreaName());
		}
		System.out.println("------>");
		List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
		for (CountryArea c : ca2) {
			System.out.println(c.getAreaId() + ":" + c.getAreaName());
		}
		System.out.println("------>");
		List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
		for (CountryArea c : ca3) {
			System.out.println(c.getAreaId() + ":" + c.getAreaName());
		}
		System.out.println("------>");
		
		List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
		for (CountryArea c : ca4) {
			System.out.println(c.getAreaId() + ":" + c.getAreaName());
		}
*/		
		StudentOrder so = buildStudentOrder(10);
		StudentOrderDaoImpl sodi = new StudentOrderDaoImpl();
		long count = sodi.saveStudentOrder(so);
		System.out.println(count);
		
		List<StudentOrder> orders = sodi.getStudentOrders();
		for (StudentOrder order : orders) {
			System.out.println(order.getWife().getIssueDepartment().getOfficeName() + " - " + order.getHusband().getIssueDepartment().getOfficeName());
		}

	}

	static long saveStudentOrder(StudentOrder studentOrder) {
		long answer = 199;
		System.out.println("saveStudentOrder:");
		
		return answer;
	}
	
	static StudentOrder buildStudentOrder(long id) {
		StudentOrder so = new StudentOrder();
		so.setStudentOrderID(id);
		RegisterOffice ro = new RegisterOffice(1L, "", "");
		so.setMarriageOffice(ro);
		so.setMarriageSertificateID("" + (100000 + id));
		so.setMarriageDate(LocalDate.of(2008, 11, 8));
		
		Street street = new Street(1L, "First Street");
		Address address = new Address("670139", street, "17", "", "118");
		
		Adult husband = new Adult("Иванов", "Григорий", "Петрович", LocalDate.of(1983, 11, 21));
		husband.setPassportNumber("" + (100000 + id));
		husband.setPassportSeria("" + (1000 + id));
		husband.setIssueDate(LocalDate.of(2000, 4, 16));
		PassportOffice po1 = new PassportOffice(1L, "", "");
		husband.setIssueDepartment(po1);
		husband.setStudentID("" + (100 + id));
		husband.setUniversity(new University(2L, ""));
		husband.setStudentID("HH12345");
		husband.setAddress(address);
		
		Adult wife = new Adult("Иванова","Екатерина","Олеговна", LocalDate.of(1991, 5, 13));
		wife.setPassportNumber("" + (200000 + id));
		wife.setPassportSeria("" + (2000 + id));
		wife.setIssueDate(LocalDate.of(2001, 5, 19));
		PassportOffice po2 = new PassportOffice(2L, "", "");
		wife.setIssueDepartment(po2);
		wife.setStudentID("" + (200 + id));
		wife.setUniversity(new University(1L, ""));
		wife.setStudentID("WW12345");
		wife.setAddress(address);
		
		Child child1 = new Child("Иванов","Лев","Григорьевич", LocalDate.of(2010, 3, 17));
		child1.setCertificateNumber("" + (3000000 + id));
		child1.setIssueDate(LocalDate.of(2010, 3, 19));
		RegisterOffice ro2 = new RegisterOffice(2L, "", "");
		child1.setIssueDepartmen(ro2);
		child1.setAddress(address);
		
		Child child2 = new Child("Иванов","Валерий","Григорьевич", LocalDate.of(2010, 3, 17));
		child2.setCertificateNumber("" + (4000000 + id));
		child2.setIssueDate(LocalDate.of(2010, 5, 7));
		RegisterOffice ro3 = new RegisterOffice(3L, "", "");
		child2.setIssueDepartmen(ro3);
		child2.setAddress(address);

		so.setHusband(husband);
		so.setWife(wife);
		so.addChild(child1);
		so.addChild(child2);
		
		return so;
		}
}
