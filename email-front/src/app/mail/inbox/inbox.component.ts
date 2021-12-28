import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ApiService } from 'src/app/api/api.service';
import { Globals } from 'src/app/globals/Globals';
import { Email } from '../email';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css'],
})
export class InboxComponent implements OnInit{
  @ViewChild('myDisplay') myDisp!: ElementRef;
  to!: Array<string>;
  subject!: string;
  from!:string;
  content!: string;
  date!: string;
  email!: Email;
  emails: Email[] = [];
  allEmails: Email[] = [];
  checkboxes: any[] = [
    {value: '1', checked: false },
    {value: '2', checked: false },
    {value: '3', checked: false },
    {value: '4', checked: false },
    {value: '5', checked: false },
    {value: '6', checked: false },
    {value: '7', checked: false },
    {value: '8', checked: false },
    {value: '9', checked: false },
    {value: '10', checked: false }
  ]
  selectAll: boolean = false;
  pageNumber: number = 1;

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.getEmails(Globals.username, "Inbox").subscribe(
      (mailList: Array<Email>) => {
        mailList.forEach(
          (email: Email) => {
            this.allEmails.push(Email.createEmailFromObject(email));
            console.log(typeof (Email.createEmailFromObject(email)));
          }
        )
      },
    () => {},
    () => {
      this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
    }
    )

  }

  on(index : number) {
    this.myDisp.nativeElement.style.display = 'block';
    this.from = this.emails[index].getSender();
    this.to = this.emails[index].getReceiver();
    this.subject = this.emails[index].getSubject();
    this.content = this.emails[index].getBody();
    this.date = this.emails[index].getDate();
  }
  off(){
    this.myDisp.nativeElement.style.display = 'none';
  }

  toggleCheckboxes() {
    this.checkboxes.forEach(checkbox => {
      checkbox.checked = this.selectAll;
    });
  }

  next(){
    this.pageNumber += 1;
    this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
  }

  prev(){
    this.pageNumber -= 1;
    this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
  }
}
