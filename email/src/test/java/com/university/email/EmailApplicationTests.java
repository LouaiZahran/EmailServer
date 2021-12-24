package com.university.email;

import com.university.email.model.credentials.Credential;
import com.university.email.model.criteria.Criteria;
import com.university.email.model.criteria.CriteriaPriority;
import com.university.email.model.email.Email;
import com.university.email.model.user.User;
import com.university.email.services.dao.DAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EmailApplicationTests {
	private final DAO dao = DAO.getInstance();

	@Test
	void contextLoads() {
		Credential credential = new Credential("Louai", "123");
		dao.addUser(new User(credential));

		credential = new Credential("Bahaa", "456");
		dao.addUser(new User(credential));

		Email email = new Email("Louai", "Bahaa", "Demo", "Hello World!", null, "1");

		User Louai = dao.findUserByUsername("Louai");
		User Bahaa = dao.findUserByUsername("Bahaa");
		Louai.sendEmail(email);

		email = new Email("Louai", "Bahaa", "Demo 2", "Hello Filter!", null, "2");
		Louai.sendEmail(email);

		Criteria criteria = new CriteriaPriority("2");
		ArrayList<Email> filtered = Bahaa.getFolder("Inbox").search(criteria);
		for(Email filteredEmail: filtered) {
			filteredEmail.print();
		}
	}

}
