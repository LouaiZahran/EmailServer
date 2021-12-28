import { Email } from "./email";
export class GetEmails {
    allEmails: Email[] = [];
    mails: Email[] = [];
    constructor(){
        //this.email = new Email('from', 'to', 'subject', 'body', 0);
        //this.allEmails.push(this.email);
        for (let index = 0; index < 25; index++) {
            this.allEmails.push(new Email('from'+index, 'to'+index, 'subject', 'from'+index, 0, false));
            //this.allEmails.splice
            }
        for (let index = 5; index < 20; index++) {
            this.allEmails[index].toggleRead();
        }
        for (let index = 5; index < 10; index++) {
            this.allEmails[index].toggleDraft();
        }
        for (let index = 9; index < 14; index++) {
            this.allEmails[index].toggleTrash();
        }
    }
    getInbox(){
        this.mails = [];
        this.allEmails.forEach(email => {
            if(!(email.getDraft() || email.getTrash()))
                this.mails.push(email);
        });
        return this.mails;
    }
    getSent(){
        this.mails = [];
        this.allEmails.forEach(email => {
            if(!(email.getDraft() || email.getTrash()))
                this.mails.push(email);
        });
        return this.mails;
    }
    getTrash(){
        this.mails = [];
        this.allEmails.forEach(email => {
            if(email.getTrash())
                this.mails.push(email);
        });
        return this.mails;
    }
    getDraft(){
        this.mails = [];
        this.allEmails.forEach(email => {
            if(email.getDraft())
                this.mails.push(email);
        });
        return this.mails;
    }
}
