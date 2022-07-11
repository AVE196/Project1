package edu.studentorder.validator;
import edu.studentorder.domain.AnswerStudent;
import edu.studentorder.domain.StudentOrder;

public class StudentValidator {

	String schoolName;
	
	public AnswerStudent checkStudent(StudentOrder so) {
		System.out.println("checkStudent is runnning");
		AnswerStudent ans = new AnswerStudent();
		ans.success = false;
		return ans;
	}
}
