package edu.studentorder.validator;

import edu.studentorder.domain.CityRegisterCheckerResponse;
import edu.studentorder.domain.Person;
import edu.studentorder.exception.CityRegisterException;
import edu.studentorder.domain.Adult;
import edu.studentorder.domain.Child;

public class FakeCityRegisterChecker implements CityRegisterChecker{
	
		final private static String GOOD1 = "100000";
		final private static String GOOD2 = "200000";
		final private static String BAD1 = "100001";
		final private static String BAD2 = "200001";
		final private static String ERROR1 = "100002";
		final private static String ERROR2 = "200002";
		private CityRegisterCheckerResponse resp = new CityRegisterCheckerResponse();

public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException{
		
		if (person instanceof Adult) {
			Adult ps = (Adult)person;
			
			if (ps.getPassportNumber().equals(GOOD1) || ps.getPassportNumber().equals(GOOD2)) {
				resp.setExisting(true);
				resp.setTemporal(false);
				System.out.println(resp.toString() + " " + ps.getSurName() + " " + ps.getGivenName());
				return resp;
			}
			if (ps.getPassportNumber().equals(BAD1) || ps.getPassportNumber().equals(BAD2)) {
				resp.setExisting(false);
				System.out.println(resp.toString() + " " + ps.getSurName() + " " + ps.getGivenName());
				return resp;
			}
			if (ps.getPassportNumber().equals(ERROR1) || ps.getPassportNumber().equals(ERROR2)) {
				CityRegisterException ex = new CityRegisterException("Error check");
				throw ex;
			}
		}
			
		if (person instanceof Child) {
			Child ps = (Child)person;
			resp.setExisting(true);
			resp.setTemporal(true);
			System.out.println(resp.toString() + " " + ps.getSurName() + " " + ps.getGivenName());
			return resp;
		}
		
		return null;
	}
	
}


