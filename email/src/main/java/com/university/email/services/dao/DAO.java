package com.university.email.services.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.NullUser;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DAO implements IDAO{
    private static DAO instance = new DAO();
    private ArrayList<UserInterface> users = new ArrayList<>();

    private DAO(){};

    public static DAO getInstance() {
        return instance;
    }
    @Override
    public ArrayList<UserInterface> getUsers() {
        return users;
    }
    @Override
    public void setUsers(ArrayList<UserInterface> users) {
        this.users = users;
    }

    @Override
    public void saveDAO(){
        File file=new File("DAO/users.json");
        try {
            ObjectMapper mapper=new ObjectMapper();
            String jsonStr=mapper.writeValueAsString(users);
            System.out.println("\n"+jsonStr);
            FileWriter writer = new FileWriter(file);
            writer.write(jsonStr);
            writer.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void loadDAO(){
        try {
            ObjectMapper mapper=new ObjectMapper();
            String load="";
            File file=new File("DAO/users.json");
            Scanner scan=new Scanner(file);
            while (scan.hasNextLine()){
                load+=scan.nextLine();
            }
            scan.close();

            instance.setUsers(new ArrayList<UserInterface>(
                    mapper.readValue(new File("DAO/users.json"),
                    new TypeReference<ArrayList<User>>() {
                    })));
        }
        catch(IOException e){
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
