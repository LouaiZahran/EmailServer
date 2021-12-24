package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.User;

import java.util.ArrayList;

public class DAO implements IDAO{
    private static DAO instance = new DAO();
    private ArrayList<User> users = new ArrayList<>();

    private DAO(){};

    public static DAO getInstance() {
        return instance;
    }

    @Override
    public User findUserByCredentials(Credential credential) {
        for(User user: users){
            if(user.getCredential().equals(credential))
                return user;
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        for(User user: users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
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
