package com.university.email.model.user;

import com.university.email.model.contact.Contact;
import com.university.email.model.credentials.Credential;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;

import java.util.ArrayList;

public class NullUser implements UserInterface{
    @Override
    public boolean isNill() {
        return true;
    }
    @Override
    public Credential getCredential() {
        return null;
    }
    @Override
    public String getUsername(){
        return "This is a null user";
    }
    @Override
    public Folder getFolder(String name){
        return null;
    }
    @Override
    public ArrayList<Folder> getFolders(){
        return null;
    }
    @Override
    public ArrayList<Contact> getContacts(){
        return null;
    }
    @Override
    public void sendEmail(Email email){
        return;
    }
    @Override
    public void addFolder(String name){
        return;
    }
    @Override
    public void removeFolder(String name){
        return;
    }
    @Override
    public void addContact(Contact contact){
        return;
    }
    @Override
    public void removeContact(Contact contact){
        return;
    }
}
