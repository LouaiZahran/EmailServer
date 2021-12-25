package com.university.email.model.UserCommandPattern;

import com.university.email.model.folder.Folder;

import java.util.ArrayList;

public class AddFolder implements UserCommandInterface{
    private String name;
    private final ArrayList<Folder> folders;
    public AddFolder(String name,ArrayList<Folder> folders)
    {
        this.name=name;
        this.folders=folders;
    }
    @Override
    public void execute(){
        folders.add(new Folder(name, new ArrayList<>()));
    }
}
