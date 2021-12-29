import { Injectable } from '@angular/core';
import { ApiService } from './api/api.service';
import { Globals } from './globals/Globals';
import { Email } from './mail/email';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private api:ApiService) { }

  search(searchString:string,filter:Array<string>,allEmails:Array<Email>,foldername:string,pageNumber:number,emails:Email[]){
    this.api.filterEmails(Globals.username, foldername,filter,searchString).subscribe(
      (mailList: Array<Email>) => {
        allEmails = [];
        mailList.forEach(
          (email: Email) => {
            allEmails.push(Email.createEmailFromObject(email));
          }
        )
      },
    () => {},
    () => {
      allEmails.reverse();
      emails = allEmails.slice((pageNumber - 1) * 10, allEmails.length - (pageNumber - 1) * 10 > 10 ? pageNumber * 10 : allEmails.length);
    }
    )
  }
  
}
