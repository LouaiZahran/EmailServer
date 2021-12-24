package com.university.email.services;

import com.university.email.services.dao.DAO;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;
import com.university.email.model.user.User;


public class EmailSender {
    private static EmailSender instance = new EmailSender();
    private DAO dao = DAO.getInstance();

    private EmailSender(){}

    public static EmailSender getInstance(){
        return instance;
    }

    public void send(Email email){
        User sender = dao.findUserByUsername(email.getSender());
        User receiver = dao.findUserByUsername(email.getReceiver());

        Folder sentFolder = sender.getFolder("Sent");
        Folder inboxFolder = receiver.getFolder("Inbox");

        sentFolder.addEmail(email);
        inboxFolder.addEmail(email);
    }
}
