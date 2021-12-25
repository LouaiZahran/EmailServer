package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;

public interface IDAO {
    UserInterface findUserByCredentials(Credential credential);
    UserInterface findUserByUsername(String username);
    void addUser(User user);
    void removeUser(User user);
}
