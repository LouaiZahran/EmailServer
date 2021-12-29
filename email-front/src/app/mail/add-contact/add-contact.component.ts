import { Component, OnInit } from '@angular/core';
import { Contact } from '../contact';
import { ApiService } from 'src/app/api/api.service';
@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
  
})
export class AddContactComponent implements OnInit {

  
  
  createContact(contactName:string,info :string){
    let infos=info.split("\n");
    let contact = new Contact(contactName,infos);
    this.api.send("/addContact", contact).subscribe();
  }
  constructor(private api: ApiService) {
    
  }

  ngOnInit(): void {
  }


}
