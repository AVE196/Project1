package edu.studentorder.validator;

import edu.studentorder.domain.CityRegisterCheckerResponse;
import edu.studentorder.domain.Person;
import edu.studentorder.exception.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker{

public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException{
		
		return null;
	}
	
}
