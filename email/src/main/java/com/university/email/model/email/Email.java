package com.university.email.model.email;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Email implements Comparable<Email> {
    private String sender;
    private Queue<String> receiver=new LinkedList<>();
    private String subject;
    private String body;
    private Object attachment;
    private Integer priority;

    public Email(EmailBuilder builder){
        this.sender=builder.getSender();
        this.receiver.addAll(builder.getReceiver());
        this.subject=builder.getSubject();
        this.body=builder.getBody();
        this.attachment=builder.getAttachment();
        this.priority=Integer.valueOf(builder.getPriority());
    }
    public void print(){
        System.out.println("");
        System.out.printf("Sender: %s%n", sender);
        ArrayList<String> receiversArr=new ArrayList<>(getReceiver());
        for(int i=0;i< receiversArr.size();i++)
            System.out.printf("Receiver %d: %s%n", i,receiversArr.get(i));
        System.out.printf("Subject: %s%n", subject);
        System.out.printf("Body: %s%n", body);
        System.out.printf("Priority: %s%n", priority);
    }
    @Override
    public int compareTo(Email email){
        return this.getPriority().compareTo(email.getPriority());
    }


}
