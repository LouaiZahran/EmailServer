package com.university.email.model.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Contact {
    private String name;
    private ArrayList<String> addressList;
    private String username;
}
