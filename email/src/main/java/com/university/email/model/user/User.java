package com.university.email.model.user;

import com.university.email.model.contact.Contact;
import com.university.email.model.credentials.Credential;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;
import com.university.email.services.EmailSender;

import java.util.ArrayList;

public class User {
    private final Credential credential;
    private final ArrayList<Folder> folders;
    private final ArrayList<Contact> contacts;
    private final EmailSender emailSender;

    public User(Credential credential){
        this.credential = credential;
        folders = new ArrayList<>();
        folders.add(new Folder("Inbox"));
        folders.add(new Folder("Sent"));
        contacts = new ArrayList<>();
        emailSender = EmailSender.getInstance();
    }

    public Credential getCredential() {
        return credential;
    }

    public String getUsername(){
        return credential.getUsername();
    }

    public Folder getFolder(String name){
        for(Folder folder: folders)
            if(folder.getName().equals(name))
                return folder;
        return null;
    }

    public ArrayList<Folder> getFolders(){
        return folders;
    }

    public ArrayList<Contact> getContacts(){
        return contacts;
    }

    public void sendEmail(Email email){
        emailSender.send(email);
    }

    public void addFolder(String name){
        folders.add(new Folder(name, new ArrayList<>()));
    }

    public void removeFolder(String name){
        folders.removeIf(folder -> folder.getName().equals(name));
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void removeContact(Contact contact){
        contacts.remove(contact);
    }
}
