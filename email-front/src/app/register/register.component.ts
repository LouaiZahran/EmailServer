import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api/api.service';
import { Globals } from '../globals/Globals';
import { Credential } from '../credential/Credential';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register(username: string, password: string){
    Globals.username = username;
    Globals.password = password;
    this.api.send("/signup", new Credential(username, password)).subscribe(
      () => {},
      (error) => {
        alert("Username is already used");
      },
      () => {
        this.router.navigate(["/mail/inbox"]);
      }
    );
  }

  constructor(private api: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

}
