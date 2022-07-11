package edu.studentorder;
import edu.studentorder.domain.Adult;
import edu.studentorder.domain.StudentOrder;

public class SaveStudentOrder {

	
	
	public static void main(String[] args) {
//		buildStudentOrder();
		
		//		StudentOrder so = new StudentOrder();
//		long ans = saveStudentOrder(so);
	//	System.out.println(ans);
	}

	static long saveStudentOrder(StudentOrder studentOrder) {
		long answer = 199;
		System.out.println("saveStudentOrder:");
		
		return answer;
	}
	
	static StudentOrder buildStudentOrder(long id) {
		StudentOrder so = new StudentOrder();
		so.setStudentOrderID(id);
		
		Adult husband = new Adult("Иванов", "Иван", "Иваныч", null);
		

		
		return so;
		}
}
