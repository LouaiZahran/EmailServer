import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ApiService } from 'src/app/api/api.service';
import { Globals } from 'src/app/globals/Globals';
import { Email } from '../email';
import { DraftComponent } from '../draft/draft.component';
@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  static draftTo:Array<string>;
  static draftSubject:string;
  static draftContent:string;
  static draftEdit:boolean;
  to!:string;
  subject!:string;
  content!:string;
  selectedFile:any;

  attach(event: any){
    const selectedFile = <File>event.target.files[0];
    console.log(selectedFile);
  }
  // receiver: string, subject:string, content:string
  createEmail(){
    const fd=new FormData();
    var obj=this.selectedFile;
    if(obj!=null)
      fd.append('example',obj,obj.name);
    let receivers=this.to.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    console.log(priority);
    let email = new Email(Globals.username, receivers, this.subject, this.content,Number(priority),true,fd);
    this.api.send("/sendEmail", email).subscribe();
  }
  // receiver: string, subject:string, content:string
  saveDraft(){
    const fd=new FormData();
    var obj=this.selectedFile;
    if(obj!=null)
      fd.append('example',obj,obj.name);
    let receivers=this.to.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    let email = new Email(Globals.username, receivers, this.subject, this.content,Number(priority), true,fd);
    this.api.send("/saveDraft", email).subscribe();
  }


upload(){
  // this.api.send()
}


  constructor(private api: ApiService) { }
  ngOnInit(): void {
    if(ComposeComponent.draftEdit == true){
      this.to = ComposeComponent.draftTo.toString();
      this.subject = ComposeComponent.draftSubject;
      this.content = ComposeComponent.draftContent;
      ComposeComponent.draftEdit = false;
    }
  }
}
