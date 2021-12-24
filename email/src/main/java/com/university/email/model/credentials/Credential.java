package com.university.email.model.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Credential{
    private String username;
    private String password;

    @Override
    public boolean equals(Object credential){
        Credential combination = (Credential) credential;
        return username.equalsIgnoreCase(combination.getUsername()) && password.equals((combination.getPassword()));
    }
}
