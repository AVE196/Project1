package edu.studentorder;
import edu.studentorder.domain.AnswerChildren;
import edu.studentorder.domain.AnswerCityRegister;
import edu.studentorder.domain.AnswerStudent;
import edu.studentorder.domain.AnswerWedding;
import edu.studentorder.domain.StudentOrder;
import edu.studentorder.mail.MailSender;
import edu.studentorder.validator.ChildrenValidator;
import edu.studentorder.validator.CityRegisterValidator;
import edu.studentorder.validator.StudentValidator;
import edu.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {
	
	private CityRegisterValidator cityRegisterValidator;
	private ChildrenValidator childrenValidator;
	private StudentValidator studentValidator;
	private WeddingValidator weddingValidator;
	private MailSender mailSender;
	
	public StudentOrderValidator () {
		cityRegisterValidator = new CityRegisterValidator();
		childrenValidator = new ChildrenValidator();
		studentValidator = new StudentValidator();
		weddingValidator = new WeddingValidator();
		mailSender = new MailSender();
		
	}

	public static void main(String[] args) {
		new StudentOrderValidator().checkAll();
	}
	
	public void checkAll() {
		StudentOrder[] soArray = readStudentOrders();
		for(StudentOrder so : soArray) {
			checkOneOrder(so);
		}
	}
	
	public StudentOrder[] readStudentOrders() {
		StudentOrder[] soArray = new StudentOrder[3];
		for (int i = 0; i < soArray.length; i++) {
			soArray[i] = SaveStudentOrder.buildStudentOrder(i);
		}
		return soArray;
	}
	
	public void checkOneOrder(StudentOrder so) {
		AnswerCityRegister cityAns = checkCityRegister(so);
		//AnswerWedding wedAns = checkWedding(so);
		//AnswerChildren childAns = checkChildren(so);
		//AnswerStudent studAns = checkStudent(so);
		
		sendMail(so);		
	}
	
	public AnswerCityRegister checkCityRegister(StudentOrder so) {
		return cityRegisterValidator.checkCityRegister(so);
	}

	public AnswerWedding checkWedding(StudentOrder so) {
		return weddingValidator.checkWedding(so);
	}
	
	public AnswerChildren checkChildren(StudentOrder so) {
		return childrenValidator.checkChildren(so);
	}
	
	public AnswerStudent checkStudent(StudentOrder so) {
		return studentValidator.checkStudent(so);
	}
	
	public void sendMail(StudentOrder so) {
		mailSender.sendMail(so);
	}
}
