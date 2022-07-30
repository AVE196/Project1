package edu.studentorder;
import java.util.LinkedList;
import java.util.List;

import edu.studentorder.domain.StudentOrder;
import edu.studentorder.domain.children.AnswerChildren;
import edu.studentorder.domain.register.AnswerCityRegister;
import edu.studentorder.domain.student.AnswerStudent;
import edu.studentorder.domain.wedding.AnswerWedding;
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
		List<StudentOrder> soArray = readStudentOrders();
		for(StudentOrder so : soArray) {
			checkOneOrder(so);
		}
	}
	
	public List<StudentOrder> readStudentOrders() {
		List<StudentOrder> soList = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
			soList.add(so);
		}
		return soList;
	}
	
	public void checkOneOrder(StudentOrder so) {
		AnswerCityRegister cityAns = checkCityRegister(so);
		//AnswerWedding wedAns = checkWedding(so);
		//AnswerChildren childAns = checkChildren(so);
		//AnswerStudent studAns = checkStudent(so);
		
		// sendMail(so);		
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
