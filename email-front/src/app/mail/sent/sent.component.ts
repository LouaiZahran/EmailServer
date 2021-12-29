import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ApiService } from 'src/app/api/api.service';
import { Globals } from 'src/app/globals/Globals';
import { Email } from '../email';

@Component({
  selector: 'app-sent',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent implements OnInit {

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
  prioSort: boolean = true;
  searchString: string = '';
  filterTo:boolean = false;
  filterFrom:boolean = false;
  filterBody:boolean = false;
  filterSubject:boolean = false;
  
  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.api.getEmails(Globals.username, "Sent").subscribe(
      (mailList: Array<Email>) => {
        this.allEmails=[];
        mailList.forEach(
          (email: Email) => {
            this.allEmails.push(Email.createEmailFromObject(email));
          }
        )
      },
    () => {},
    () => {
      this.allEmails.reverse();
      this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
        for (let i=0;i<10;i++){
          this.checkboxes[i].checked=false;
        }
      }
    )
  }
  search(){
    if(!this.filterBody && !this.filterFrom && !this.filterSubject && !this.filterTo && this.searchString.length==0)
      this.load();
    var filters=Array<string>();
    if(this.filterTo){
      filters.push("to");
    }
    if(this.filterFrom){
      filters.push("from");
    }
    if(this.filterBody){
      filters.push("Body");
    }
    if(this.filterSubject){
      filters.push("Subject");
    }
   this.api.filterEmails(Globals.username, "Inbox",filters,this.searchString).subscribe(
    (mailList: Array<Email>) => {
      this.allEmails = [];
      mailList.forEach(
        (email: Email) => {
          this.allEmails.push(Email.createEmailFromObject(email));
        }
      )
    },
  () => {},
  () => {
    this.allEmails.reverse();
    this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
    for (let i=0;i<10;i++){
      this.checkboxes[i].checked=false;
    }
    }
  )
     
  }
  sortEmails(){
    this.api.sortEmails(Globals.username, "Sent").subscribe(
      (mailList: Array<Email>) => {
        this.allEmails = [];
        mailList.forEach(
          (email: Email) => {
            this.allEmails.push(Email.createEmailFromObject(email));
          }
        )
      },
    () => {},
    () => {
      this.allEmails.reverse();
      this.emails = this.allEmails.slice((this.pageNumber - 1) * 10, this.allEmails.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allEmails.length);
      for (let i=0;i<10;i++){
        this.checkboxes[i].checked=false;
      }
    }
    )

  }
  deleteEmails(){
    for(let i = 0; i<10; i++){
      if(!this.checkboxes[i].checked)
        continue;
        this.api.moveEmail((this.allEmails.length-((this.pageNumber-1)*10+i)-1),"Sent","Trash").subscribe();
    }

    this.load();
  }
  

  on(index : number) {
    this.myDisp.nativeElement.style.display = 'block';
    this.to = this.emails[index].getTo();
    this.from = this.emails[index].getFrom();
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
  filterAdvanced: boolean = false;
  @ViewChild('filter') myFilter!: ElementRef;
  toggle(){
    this.filterAdvanced = !this.filterAdvanced;
    if(this.filterAdvanced){
      this.myFilter.nativeElement.style.display = 'block';
    }
    else{
      this.myFilter.nativeElement.style.display = 'none';
    }
  }

}
