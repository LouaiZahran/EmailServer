package com.university.email.services.dao;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.NullUser;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;

import com.google.gson.Gson;


public class DAO implements IDAO{
    private static DAO instance = new DAO();
    private ArrayList<UserInterface> users = new ArrayList<>();
    private Gson gson=new Gson();

    private DAO(){};

    public static DAO getInstance() {
        return instance;
    }

    public void saveDAO(){
        File file=new File("DAO/DAO.json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(users.size()==0?"{}":gson.toJson(users));
            writer.close();
        }
        catch(InaccessibleObjectException|IOException e){
            System.out.println(e.getMessage());
        }
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
