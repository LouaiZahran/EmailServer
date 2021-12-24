package com.university.email.model.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Email {
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private Object attachment;
    private String priority;

    public void print(){
        System.out.println("");
        System.out.printf("Sender: %s%n", sender);
        System.out.printf("Receiver: %s%n", receiver);
        System.out.printf("Subject: %s%n", subject);
        System.out.printf("Body: %s%n", body);
        System.out.printf("Priority: %s%n", priority);
    }
}
