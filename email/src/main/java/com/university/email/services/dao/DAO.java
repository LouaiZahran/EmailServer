package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.NullUser;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;

import java.util.ArrayList;

public class DAO implements IDAO{
    private static DAO instance = new DAO();
    private ArrayList<UserInterface> users = new ArrayList<>();

    private DAO(){};

    public static DAO getInstance() {
        return instance;
    }

    @Override
    public UserInterface findUserByCredentials(Credential credential) {
        for(UserInterface user: users){
            if(user.getCredential().equals(credential))
                return user;
        }
        return new NullUser();
    }

    @Override
    public UserInterface findUserByUsername(String username) {
        for(UserInterface user: users){
            if(user.getUsername().equals(username))
                return user;
        }
        return new NullUser();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }
}
