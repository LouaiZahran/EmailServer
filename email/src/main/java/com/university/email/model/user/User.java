package com.university.email.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.email.model.contact.Contact;
import com.university.email.model.credentials.Credential;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;
import com.university.email.services.EmailSender;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserInterface {
    private final Credential credential;
    private final ArrayList<Folder> folders;
    private final ArrayList<Contact> contacts;
    private final EmailSender emailSender;



    public User(Credential credential){
        this.credential = credential;
        folders = new ArrayList<>();
        folders.add(new Folder("Inbox"));
        folders.add(new Folder("Sent"));
        folders.add(new Folder("Trash"));
        folders.add(new Folder("Draft"));
        contacts = new ArrayList<>();
        emailSender = EmailSender.getInstance();
    }
    public User(){
        credential=new Credential();
        folders=new ArrayList<>();
        contacts=new ArrayList<>();
        emailSender=EmailSender.getInstance();
    }
    @Override
    public boolean isNill(){
        return false;
    }
    @Override
    public Credential getCredential() {
        return credential;
    }
    @Override
    public String getUsername(){
        return credential.getUsername();
    }
    @Override
    public Folder getFolder(String name){
        for(Folder folder: folders)
            if(folder.getName().equalsIgnoreCase(name))
                return folder;
        return null;
    }
    @Override
    public ArrayList<Folder> getFolders(){
        return folders;
    }
    @Override
    public ArrayList<Contact> getContacts(){
        return contacts;
    }
    @Override
    public void sendEmail(Email email){
        emailSender.send(email);
    }
    @Override
    public void addFolder(String name){
        folders.add(new Folder(name, new ArrayList<>()));
    }
    @Override
    public void removeFolder(String name){
        folders.removeIf(folder -> folder.getName().equals(name));
    }
    @Override
    public ArrayList<Contact> findContact(String name){
        ArrayList<Contact> ret=new ArrayList<>();
        for(Contact contact: contacts){
            if(contact.getName().indexOf(name)!=-1){
                ret.add(contact);
            }
        }
        return ret;
    }
    @Override
    public void addContact(Contact contact){
        contacts.add(contact);
    }
    @Override
    public void removeContact(int index){
        contacts.remove(index);
    }
}
