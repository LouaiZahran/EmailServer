package com.university.email.controller;

import com.university.email.model.contact.Contact;
import com.university.email.model.credentials.Credential;
import com.university.email.model.criteria.*;
import com.university.email.model.email.Email;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.university.email.services.dao.DAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class ServerController {
    private final DAO dao = DAO.getInstance();

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody Credential credential){
        if(dao.findUserByCredentials(credential).isNill()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        UserInterface sender = (UserInterface) dao.findUserByUsername(email.getSender());
        if(!sender.isNill())
            sender.sendEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addContact")
    public ResponseEntity<String> addContact(@RequestBody Contact contact){
        UserInterface user =  (UserInterface) dao.findUserByUsername(contact.getUsername());
        if(!user.isNill())
            user.addContact(contact);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getContacts")
    public ArrayList<Contact> getContacts(@RequestParam String username){
        UserInterface user = dao.findUserByUsername(username);
        if(user.isNill() || user.getContacts() == null)
            return null;
        return user.getContacts();
    }

    @GetMapping("/getEmails")
    public ArrayList<Email> getEmails(@RequestParam String username, @RequestParam String folder){
        UserInterface user = dao.findUserByUsername(username);
        if(user.isNill() || user.getFolder(folder) == null)
            return null;
        return user.getFolder(folder).getContent();
    }
    @GetMapping("/sortEmails")
    public ArrayList<Email> sortEmails(@RequestParam String username, @RequestParam String folder) {
        UserInterface user = dao.findUserByUsername(username);
        if(user.isNill() || user.getFolder(folder) == null)
            return null;
        PriorityQueue<Email> pq=new PriorityQueue<>();
        for(int i=0;i<user.getFolder(folder).getContent().size();i++){
            pq.add(user.getFolder(folder).getContent().get(i));
        }
        return new ArrayList<>(pq);
    }
    @GetMapping("/filterEmails")
    public ArrayList<Email> filter(@RequestParam String username, @RequestParam String folder,
                                   @RequestParam ArrayList<String> filter,@RequestParam String search) {
        UserInterface user = dao.findUserByUsername(username);
        if(user.isNill() || user.getFolder(folder) == null)
            return null;
        Criteria criteria=null;
        if(filter.contains("from")){
            criteria=new CriteriaSender(search);
        }
        else if(filter.contains("to")){
            criteria=new CriteriaReceiver(search);
        }
        else if(filter.contains("Body")){
            criteria=new CriteriaBody(search);
        }
        else if(filter.contains("Subject")){
            criteria=new CriteriaSubject(search);
        }
        else{
            return new ArrayList<>();
        }
        return user.getFolder(folder).search(criteria);
    }
    @PostMapping("/saveDraft")
    public ResponseEntity<String> saveDraft(@RequestBody Email email){
        UserInterface sender = (UserInterface) dao.findUserByUsername(email.getSender());
        if(!sender.isNill())
            sender.getFolder("Draft").addEmail(email);
        dao.saveDAO();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Credential credential){
        if(!dao.findUserByUsername(credential.getUsername()).isNill())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        dao.addUser(new User(credential));
        dao.saveDAO();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/deleteContact")
    public ResponseEntity<String> deleteContact(@RequestBody contactDeleteParams deletion){
        try {
            dao.findUserByUsername(deletion.getUsername()).removeContact(deletion.getIndex());
        }catch(Exception e){

        }
        dao.saveDAO();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/deleteEmail")
    public ResponseEntity<String> deleteEmail(@RequestBody emailDeleteParams deletion){
        try {
            dao.deleteEmail(deletion.getUsername(),deletion.getFolderName(),deletion.getIndex());
        }catch(Exception e){

        }
        dao.saveDAO();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/moveEmail")
    public ResponseEntity<String> moveToTrash(@RequestBody emailMoveParams move){
        try {
            dao.moveEmail(move.getUsername(), move.getOldFolderName(),move.getNewFolderName(),move.getIndex());
        }catch(Exception e){

        }
        dao.saveDAO();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
