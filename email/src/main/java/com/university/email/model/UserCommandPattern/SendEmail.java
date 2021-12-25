package com.university.email.model.UserCommandPattern;

import com.university.email.model.UserCommandPattern.UserCommandInterface;
import com.university.email.model.email.Email;
import com.university.email.services.EmailSender;

public class SendEmail implements UserCommandInterface {

    private final EmailSender emailSender;
    Email email;
    public SendEmail(Email email){
        this.email=email;
        emailSender=EmailSender.getInstance();
    }

    @Override
    public void execute(){
        emailSender.send(email);
    }
}
