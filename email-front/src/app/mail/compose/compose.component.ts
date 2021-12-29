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
    console.log(priority);
    let email = new Email(Globals.username, receivers, subject, content,Number(priority), true);
    this.api.send("/sendEmail", email).subscribe();
  }

  saveDraft(receiver: string, subject:string, content:string){
    let receivers=receiver.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    let email = new Email(Globals.username, receivers, subject, content,Number(priority), true);
    this.api.send("/saveDraft", email).subscribe();
  }

selectedFile = null;
attach(event: any){
  this.selectedFile = event.target.files[0];
  console.log(this.selectedFile);
}
upload(){
  const fd=new FormData();
  
  // this.api.send()
}


  constructor(private api: ApiService) { }
  ngOnInit(): void {}

}
