import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ApiService } from 'src/app/api/api.service';
import { Globals } from 'src/app/globals/Globals';
import { Email } from '../email';

@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  createEmail(receiver: string, subject:string, content:string){
    let receivers=receiver.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    let email = new Email(Globals.username, receivers, subject, content,Number(priority), true);
    this.api.send("/sendEmail", email).subscribe();
  }

  saveDraft(receiver: string, subject:string, content:string){
    let receivers=receiver.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    let email = new Email(Globals.username, receivers, subject, content,Number(priority), true);
    this.api.send("/saveDraft", email).subscribe();
  }





  constructor(private api: ApiService) { }
  ngOnInit(): void {}

}
