package com.university.email.model.user;


import com.university.email.model.contact.Contact;
import com.university.email.model.credentials.Credential;
import com.university.email.model.email.Email;
import com.university.email.model.folder.Folder;

import java.util.ArrayList;

public interface UserInterface {
    boolean isNill();
    Credential getCredential();
    String getUsername();
    Folder getFolder(String name);
    ArrayList<Folder> getFolders();
    ArrayList<Contact> getContacts();
    /*
    void sendEmail(Email email);
    void addFolder(String name);
    void removeFolder(String name);
    void addContact(Contact contact);
    void removeContact(Contact contact);*/
}
