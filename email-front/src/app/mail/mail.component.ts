import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../api/api.service';
import { InboxComponent } from './inbox/inbox.component';

@Component({
  selector: 'app-mail',
  templateUrl: './mail.component.html',
  styleUrls: ['./mail.component.css']
})
export class MailComponent implements OnInit {
  constructor() {}
  ngOnInit(): void {}

  searchString: string = '';
  filterTo:boolean = false;
  filterFrom:boolean = false;
  filterBody:boolean = false;
  filterSubject:boolean = false;
  search(){
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
    var url=window.location.href.split("/");
    var foldername=url[url.length-1];
  //  InboxComponent.search(this.searchString,filters);
  }
}
