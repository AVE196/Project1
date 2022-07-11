package edu.studentorder.validator;
import edu.studentorder.domain.AnswerCityRegister;
import edu.studentorder.domain.StudentOrder;

public class CityRegisterValidator {
	
	public String hostName;
	protected int port;
	private String login;
	String password;
	
	public AnswerCityRegister checkCityRegister(StudentOrder so) {
		System.out.println("checkCityRegister is runnning");
		AnswerCityRegister ans = new AnswerCityRegister();
		ans.success = false;
		return ans;
	}

}
