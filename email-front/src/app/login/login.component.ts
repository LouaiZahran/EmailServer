import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api/api.service';
import { Globals } from '../globals/Globals';
import { Credential } from '../credential/Credential';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login(username: string, password: string){
    Globals.username = username;
    Globals.password = password;
    this.api.send("/authenticate", new Credential(username, password)).subscribe();
  }

  constructor(private api: ApiService) { }

  ngOnInit(): void {
  }

}
