package edu.studentorder.validator;
import edu.studentorder.domain.AnswerCityRegister;
import edu.studentorder.domain.CityRegisterCheckerResponse;
import edu.studentorder.domain.StudentOrder;
import edu.studentorder.exception.CityRegisterException;

public class CityRegisterValidator {
	
	public String hostName;
	protected int port;
	private String login;
	String password;
	private CityRegisterChecker personChecker;
	
	public CityRegisterValidator() {
		personChecker = new FakeCityRegisterChecker();
	}
	
	public AnswerCityRegister checkCityRegister(StudentOrder so) {
		try {
		CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband());
		CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife());
		CityRegisterCheckerResponse cans = personChecker.checkPerson(so.getChild());
		}
		catch (CityRegisterException ex){
			ex.printStackTrace();
			
		}
	
		AnswerCityRegister ans = new AnswerCityRegister();
		return ans;
	}
}
