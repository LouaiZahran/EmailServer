package com.university.email.controller;

import com.university.email.model.credentials.Credential;
import com.university.email.model.email.Email;
import com.university.email.model.user.User;
import com.university.email.model.user.UserInterface;
import com.university.email.services.dao.DAO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping("/getEmails")
    public ArrayList<Email> getEmails(@RequestParam String username, @RequestParam String folder){
        UserInterface user = dao.findUserByUsername(username);
        if(user.isNill() || user.getFolder(folder) == null)
            return null;
        return user.getFolder(folder).getContent();
    }

}