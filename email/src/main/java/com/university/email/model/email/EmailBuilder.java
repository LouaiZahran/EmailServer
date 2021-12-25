package com.university.email.model.email;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class EmailBuilder{
    private String sender;
    private Queue<String> receiver=new LinkedList<>();
    private String subject;
    private String body;
    private Object attachment;
    private Integer priority;
    public EmailBuilder(String sender,Queue<String> receiver){
        this.sender=sender;
        this.receiver.addAll(receiver);
    }
    public EmailBuilder subject(String subject){
        this.subject=subject;
        return this;
    }
    public EmailBuilder body(String body){
        this.body=body;
        return this;
    }
    public EmailBuilder attachment(Object attachment){
        this.attachment=attachment;
        return this;
    }
    public EmailBuilder priority(Integer priority){
        this.priority=Integer.valueOf(priority);
        return this;
    }

    public Email build(){
        return new Email(this);
    }

}