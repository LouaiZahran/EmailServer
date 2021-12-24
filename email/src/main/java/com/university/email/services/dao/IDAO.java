package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.User;

public interface IDAO {
    User findUserByCredentials(Credential credential);
    User findUserByUsername(String username);
    void addUser(User user);
    void removeUser(User user);
}
