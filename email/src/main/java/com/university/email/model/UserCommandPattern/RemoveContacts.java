package com.university.email.model.UserCommandPattern;

import com.university.email.model.contact.Contact;

import java.util.ArrayList;

public class RemoveContacts implements UserCommandInterface{
    private Contact contact;
    private final ArrayList<Contact> contacts;
    public RemoveContacts(Contact contact, ArrayList<Contact> contacts){
        this.contact=contact;
        this.contacts=contacts;
    }
    @Override
    public void execute() {
        contacts.add(contact);
    }
}
