package com.university.email.model.folder;

import com.university.email.model.criteria.Criteria;
import com.university.email.model.email.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.tags.EditorAwareTag;

import java.util.ArrayList;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Folder {
    private String name;
    private ArrayList<Email> content;

    public Folder(String name){
        this.name = name;
        this.content = new ArrayList<>();
    }

    public void addEmail(Email email){
        content.add(email);
    }

    public void removeEmail(int index){
        content.remove(index);
    }

    public ArrayList<Email> search(Criteria criteria){
        ArrayList<Email> ret=criteria.meetCriteria(content);
        if(ret.size()==0)
            return new ArrayList<>();
        return ret;
    }

    public void print(){
        for(Email email: content)
            email.print();
    }
}
