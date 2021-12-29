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
  selectedFile:any;
  attach(event: any){
    const selectedFile = <File>event.target.files[0];
    console.log(selectedFile);
  }
  createEmail(receiver: string, subject:string, content:string){
    const fd=new FormData();
    var obj=this.selectedFile;
    if(obj!=null)
      fd.append('example',obj,obj.name);
    let receivers=receiver.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    console.log(priority);
    let email = new Email(Globals.username, receivers, subject, content,Number(priority),true,fd);
    this.api.send("/sendEmail", email).subscribe();
  }

  saveDraft(receiver: string, subject:string, content:string){
    const fd=new FormData();
    var obj=this.selectedFile;
    if(obj!=null)
      fd.append('example',obj,obj.name);
    let receivers=receiver.split(" ");
    var priority = (<HTMLInputElement>document.getElementById("priority")).value;
    let email = new Email(Globals.username, receivers, subject, content,Number(priority), true,fd);
    this.api.send("/saveDraft", email).subscribe();
  }


upload(){
  // this.api.send()
}


  constructor(private api: ApiService) { }
  ngOnInit(): void {}

}
