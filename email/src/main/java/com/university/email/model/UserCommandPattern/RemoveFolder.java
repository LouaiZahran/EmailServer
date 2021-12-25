package com.university.email.model.UserCommandPattern;

import com.university.email.model.folder.Folder;

import java.util.ArrayList;

public class RemoveFolder implements UserCommandInterface {
    private String name;
    private final ArrayList<Folder> folders;
    public RemoveFolder(String name,ArrayList<Folder> folders)
    {
        this.name=name;
        this.folders=folders;
    }
    @Override
    public void execute(){
        folders.removeIf(folder -> folder.getName().equals(name));
    }
}
