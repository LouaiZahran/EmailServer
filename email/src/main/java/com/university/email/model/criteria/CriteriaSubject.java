package com.university.email.model.criteria;

import com.university.email.model.email.Email;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class CriteriaSubject implements Criteria{
    private String subject;

    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> ret = new ArrayList<>();
        for(Email email: emails){
            if(email.getSubject().contains(subject))
                ret.add(email);
        }
        return ret;
    }
}
