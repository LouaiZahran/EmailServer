package com.university.email.model.criteria;

import com.university.email.model.email.Email;

import java.util.ArrayList;

public interface Criteria {
    ArrayList<Email> meetCriteria(ArrayList<Email> emails);
}
