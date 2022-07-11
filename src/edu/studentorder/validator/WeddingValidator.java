package edu.studentorder.validator;
import edu.studentorder.domain.AnswerWedding;
import edu.studentorder.domain.StudentOrder;

public class WeddingValidator {

	String placeWedding;
	
	public AnswerWedding checkWedding(StudentOrder so) {
		System.out.println("checkWedding is runnning");
		AnswerWedding ans = new AnswerWedding();
		ans.success = false;

		return ans;
	}
}
