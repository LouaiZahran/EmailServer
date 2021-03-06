package com.university.email;

import com.university.email.model.credentials.Credential;
import com.university.email.model.criteria.*;
import com.university.email.model.email.Email;
import com.university.email.model.email.EmailBuilder;
import com.university.email.model.folder.Folder;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.university.email.services.dao.DAO;
import com.university.email.services.dao.IDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailApplicationTests {
	private final IDAO dao = DAO.getInstance();
	@BeforeEach
	public void setup(){
		System.out.println("Clearing db");
		dao.setUsers(new ArrayList<>());
	}
	@Test
	void LoadTest(){
		dao.loadDAO();
		Queue<String> receivers=new LinkedList<>();
		receivers.add("Bahaa");
		UserInterface Louai = dao.findUserByUsername("Louai");
		Louai.getFolder("Sent").print();
		UserInterface Bahaa = dao.findUserByUsername("Bahaa");
		Criteria criteria = new CriteriaPriority(2);
		Bahaa.getFolder("Inbox").print();
		ArrayList<Email> filtered = Bahaa.getFolder("Inbox").search(criteria);
		for(Email filteredEmail: filtered) {
			filteredEmail.print();
			assertEquals(filteredEmail.getAttachment(),null,"Filter Test Failed");
			assertEquals(filteredEmail.getSubject(),"hello Subject 2","Filter Test Failed");
			assertEquals(filteredEmail.getBody(),"Hello Filter","Filter Test Failed");
			assertEquals(filteredEmail.getPriority(),2,"Filter Test Failed");
			assertEquals(filteredEmail.getSender(),"Louai","Filter Test Failed");
			try {
				assertEquals(filteredEmail.getReceiver().peek(), "Bahaa", "Filter Test Failed");
			}catch (Exception e){
				System.err.println(e.getMessage());
			}
		}
	}
	@Test
	void credentialCreationTest(){
		Credential credential = new Credential("Louai", "123");
		dao.addUser(new User(credential));
		assertEquals(credential.getUsername(),"Louai","Credential user name test failed");
		assertEquals(credential.getPassword(),"123","Credential password test failed");
	}
	@Test
	void emailCreationTest(){
		Queue<String> receivers=new LinkedList<>();
		receivers.add("Bahaa");
		EmailBuilder emailBuilder=new EmailBuilder("Louai",receivers).
				subject("hello subject").body("hello body").priority(1);
		Email email = new Email(emailBuilder);
		// Email Builder Test
		assertEquals(email.getReceiver().peek(),"Bahaa","Email Builder Receiver test failed");
		assertEquals(email.getSender(),"Louai","Email Builder Send test failed");
		assertEquals(email.getBody(),"hello body","Email Builder Body test failed");
		assertEquals(email.getPriority(),1,"Email Builder priority test failed");
		assertEquals(email.getSubject(),"hello subject","Email Builder subject test failed");
		assertEquals(email.getAttachment(),null,"Email Builder attachment test failed");
	}

	@Test
	void emailSendTest(){
		Credential credential = new Credential("Louai", "123");
		dao.addUser(new User(credential));

		credential = new Credential("Bahaa", "456");
		dao.addUser(new User(credential));

		Queue<String> receivers=new LinkedList<>();
		receivers.add("Bahaa");
		EmailBuilder emailBuilder=new EmailBuilder("Louai",receivers).
				subject("hello subject").body("hello body").priority(1);
		Email email = new Email(emailBuilder);

		UserInterface Louai = dao.findUserByUsername("Louai");
		UserInterface Bahaa = dao.findUserByUsername("Bahaa");
		Louai.sendEmail(email);

		assertEquals(Louai.getFolder("Sent").getContent().get(0),email,"Email failed to sent");
		assertEquals(Bahaa.getFolder("Inbox").getContent().get(0),email,"Email failed to recieve");
	}
	@Test
	void emailFilterTest(){
		Credential credential = new Credential("Louai", "123");
		dao.addUser(new User(credential));

		credential = new Credential("Bahaa", "456");
		dao.addUser(new User(credential));

		Queue<String> receivers=new LinkedList<>();
		receivers.add("Bahaa");
		Email email1=new EmailBuilder("Louai",receivers).subject("hello Subject 2")
				.body("Hello Filter").priority(2).build();

		UserInterface Louai = dao.findUserByUsername("Louai");
		UserInterface Bahaa = dao.findUserByUsername("Bahaa");
		Louai.sendEmail(email1);

		EmailBuilder emailBuilder=new EmailBuilder("Louai",receivers).
				subject("hello subject").body("hello body").priority(1);
		Email email2 = new Email(emailBuilder);
		Louai.sendEmail(email2);
		//priority
		Criteria criteria = new CriteriaPriority(2);
		ArrayList<Email> filtered = Bahaa.getFolder("Inbox").search(criteria);
		assertEquals(filtered.size(),1,"Priority Filter Test Failed ");
		for(Email filteredEmail: filtered) {
			assertEquals(filteredEmail.getPriority(),2,"Priority Filter Test Failed ");
		}
		//body criteria
		criteria = new CriteriaBody("hello body");
		filtered = Bahaa.getFolder("Inbox").search(criteria);
		assertEquals(filtered.size(),1,"Body Filter Test Failed ");
		for(Email filteredEmail: filtered) {
			assertEquals(filteredEmail.getBody(),"hello body","Body Filter Test Failed ");
		}
		//And criteria with subject and sender
		criteria = new CriteriaSubject("hello subject");
		Criteria criteria2=new CriteriaSender("Louai");
		Criteria andCriteria=new AndCriteria(criteria,criteria2);
		filtered = Bahaa.getFolder("Inbox").search(andCriteria);
		assertEquals(filtered.size(),1,"And Filter Test Failed");
		for(Email filteredEmail: filtered) {
			assertEquals(filteredEmail.getSender(),"Louai","And Filter Test Failed");
			assertEquals(filteredEmail.getSubject(),"hello subject","And Filter Test Failed");
		}
		//OR criteria
		criteria = new CriteriaSubject("not a real subject");
		criteria2=new CriteriaReceiver("Bahaa");
		Criteria or=new OrCriteria(criteria,criteria2);
		filtered = Bahaa.getFolder("Inbox").search(or);
		assertEquals(filtered.size(),2,"OR Filter Test Failed");
		for(Email filteredEmail: filtered) {
			assertEquals(filteredEmail.getReceiver().peek(),"Bahaa"," OR Filter Test Failed");
		}
	}
	@Test
	void contextLoads(){
		Credential credential = new Credential("Louai", "123");
		dao.addUser(new User(credential));

		credential = new Credential("Bahaa", "456");
		dao.addUser(new User(credential));

		Queue<String> receivers=new LinkedList<>();
		receivers.add("Bahaa");
		EmailBuilder emailBuilder=new EmailBuilder("Louai",receivers).
				subject("hello subject").body("hello body").priority(1);
		Email email = new Email(emailBuilder);

		email.print();

		UserInterface Louai = dao.findUserByUsername("Louai");
		UserInterface Bahaa = dao.findUserByUsername("Bahaa");
		Louai.sendEmail(email);

		Email exceptedEmail=new EmailBuilder("Louai",receivers).subject("hello Subject 2")
				.body("Hello Filter").priority(2).build();
		exceptedEmail.print();
		Louai.sendEmail(exceptedEmail);

		Louai.getFolder("Sent").print();
		Criteria criteria = new CriteriaPriority(2);
		Bahaa.getFolder("Inbox").print();
		ArrayList<Email> filtered = Bahaa.getFolder("Inbox").search(criteria);
		for(Email filteredEmail: filtered) {
			filteredEmail.print();
		}
		dao.saveDAO();
	}

}
