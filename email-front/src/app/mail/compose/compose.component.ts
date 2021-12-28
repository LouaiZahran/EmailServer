import { Component, OnInit } from '@angular/core';
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
    let email = new Email(Globals.username, new Array<string>(receiver), subject, content, 1, true);
    this.api.send("/sendEmail", email).subscribe(

    );
  }

  constructor(private api: ApiService) {
    
  }

  ngOnInit(): void {
  }

}
