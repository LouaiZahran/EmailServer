package com.university.email;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.User;
import com.university.email.services.dao.DAO;
import com.university.email.services.dao.IDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {

	private static final IDAO dao = DAO.getInstance();
	public static void main(String[] args) {
		dao.loadDAO();
		User user = new User(new Credential("Louai", "123"));
		dao.addUser(user);
		dao.saveDAO();
		SpringApplication.run(EmailApplication.class, args);
	}

}
