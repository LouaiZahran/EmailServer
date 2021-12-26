import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Email } from '../email';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css'],
})
export class InboxComponent implements OnInit{
  @ViewChild('myDisplay') myDisp!: ElementRef;
  email!: Email;
  emails: Email[] = [];
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

  constructor() {}

  ngOnInit(): void { 
    this.email = new Email('from', 'to', 'subject', 'body', 0);
    this.emails.push(this.email);
    for (let index = 0; index < 10; index++) {
      this.emails.push(new Email('from'+index, 'to', 'subject', 'body', 0));
    }
    
  }

  on() {
    this.myDisp.nativeElement.style.display = 'block';
  }
  off(){
    this.myDisp.nativeElement.style.display = 'none';
  }

  toggleCheckboxes() {
    this.checkboxes.forEach(checkbox => {
      checkbox.checked = this.selectAll;
    });
  }
}
