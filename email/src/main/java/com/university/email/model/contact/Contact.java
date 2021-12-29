package com.university.email.model.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Contact {
    private String name;
    private ArrayList<String> addressList;
    private String username;
}
