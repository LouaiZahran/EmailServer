package com.university.email.services.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.university.email.model.credentials.Credential;
import com.university.email.model.user.NullUser;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

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
            String path=System.getProperty("user.dir");
            ObjectMapper mapper=new ObjectMapper();
            JsonSchemaFactory schemaFactory=JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        try {
                InputStream jsonStream = new FileInputStream( path+"\\DAO\\users.json");
                InputStream schemaStream = new FileInputStream(path+"\\DAO\\usersSchema.json");
            JsonNode json = mapper.readTree(jsonStream);

            JsonSchema schema = schemaFactory.getSchema(schemaStream);

            Set<ValidationMessage> validationResult = schema.validate(json);
            if (validationResult.isEmpty()) {
                System.out.println( "There is no validation errors" );
                this.setUsers(new ArrayList<UserInterface>(
                        mapper.readValue(new File("DAO/users.json"),
                                new TypeReference<ArrayList<User>>() {
                                })));
            } else {
                validationResult.forEach(vm -> System.err.println(vm.getMessage()));
            }
        }catch (Exception e){
            e.printStackTrace();
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
