package com.university.email;

import com.university.email.model.UserCommandPattern.AddFolder;
import com.university.email.model.UserCommandPattern.SendEmail;
import com.university.email.model.UserCommandPattern.UserCommandInterface;
import com.university.email.model.credentials.Credential;
import com.university.email.model.criteria.Criteria;
import com.university.email.model.criteria.CriteriaBody;
import com.university.email.model.criteria.CriteriaPriority;
import com.university.email.model.email.Email;
import com.university.email.model.email.EmailBuilder;
import com.university.email.model.folder.Folder;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.university.email.services.dao.DAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
class EmailApplicationTests {
	private final DAO dao = DAO.getInstance();

	@Test
	void contextLoads() {
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
		//Louai.sendEmail(email);
		UserCommandInterface command=new SendEmail(email);
		command.execute();

		email=(new EmailBuilder("Louai",receivers).subject("hello Subject 2")
				.body("Hello Filter").priority(2)).build();
		email.print();
		//Louai.sendEmail(email);

		command=new SendEmail(email);
		command.execute();

		command=new AddFolder("Hi", Bahaa.getFolders());
		command.execute();
		ArrayList<Folder>folders=Bahaa.getFolders();

		Criteria criteria = new CriteriaPriority(2);
		Bahaa.getFolder("Inbox").print();
		ArrayList<Email> filtered = Bahaa.getFolder("Inbox").search(criteria);
		for(Email filteredEmail: filtered) {
			filteredEmail.print();
		}
	}

}
