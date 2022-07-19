package edu.studentorder.validator.register;

import edu.studentorder.domain.Person;
import edu.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {

	CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException;
	

}
