package com.university.email.model.criteria;

import com.university.email.model.email.Email;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class CriteriaSender implements Criteria{
    private String sender;

    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> ret = new ArrayList<>();
        for(Email email: emails){
            if(email.getSender().equals(sender))
                ret.add(email);
        }
        return ret;
    }
}
