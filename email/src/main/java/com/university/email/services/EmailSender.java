package com.university.email.services;

import com.university.email.model.user.NullUser;
import com.university.email.model.user.UserInterface;
import com.university.email.services.dao.DAO;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;
import com.university.email.model.user.User;

import java.util.ArrayList;


public class EmailSender {
    private static EmailSender instance = new EmailSender();
    private DAO dao = DAO.getInstance();

    private EmailSender(){}

    public static EmailSender getInstance(){
        return instance;
    }

    public void send(Email email){
        UserInterface sender = dao.findUserByUsername(email.getSender());
        UserInterface receiver=new NullUser();
        ArrayList<String> receiversArr=new ArrayList<>(email.getReceiver());
        for(int i=0;i< receiversArr.size();i++){
            receiver = dao.findUserByUsername(receiversArr.get(i));

            if(!sender.isNill() && !receiver.isNill()) {
                Folder sentFolder = sender.getFolder("Sent");
                Folder inboxFolder = receiver.getFolder("Inbox");

                sentFolder.addEmail(email);
                inboxFolder.addEmail(email);
            }
        }
    }
}
