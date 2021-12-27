package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;

import java.util.ArrayList;


public interface IDAO {
    ArrayList<UserInterface> getUsers();
    void setUsers(ArrayList<UserInterface> users);
    void saveDAO();
    void loadDAO();
    UserInterface findUserByCredentials(Credential credential);
    UserInterface findUserByUsername(String username);
    void addUser(User user);
    void removeUser(User user);

}
