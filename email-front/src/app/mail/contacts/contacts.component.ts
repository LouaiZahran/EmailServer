import { Component, OnInit , ViewChild, ElementRef } from '@angular/core';
import { ApiService } from 'src/app/api/api.service';
import { Globals } from 'src/app/globals/Globals';
import { Contact } from '../contact';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  @ViewChild('myDisplay') myDisp!: ElementRef;
  addressList!: Array<string>;
  name!: string;
  contact!: Contact;
  contacts: Contact[] = [];
  allcontacts: Contact[] = [];
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
  searchString:string='';
  
  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.api.getContact(Globals.username).subscribe(
      (contactList: Array<Contact>) => {
        this.allcontacts = [];
        contactList.forEach(
          (contact: Contact) => {
            this.allcontacts.push(Contact.createContactFromObject(contact));
          }
        )
      },
    () => {},
    () => {
      this.allcontacts.reverse();
      this.contacts = this.allcontacts.slice((this.pageNumber - 1) * 10, this.allcontacts.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allcontacts.length);
      for (let i=0;i<10;i++){
        this.checkboxes[i].checked=false;
      }
    }
    )
  }
  search(){
    if(this.searchString.length==0)
      this.load();
   this.api.filterContacts(Globals.username,this.searchString).subscribe(
      (contactList: Array<Contact>) => {
        this.allcontacts = [];
        contactList.forEach(
          (contact: Contact) => {
            this.allcontacts.push(Contact.createContactFromObject(contact));
          }
        )
      },
    () => {},
    () => {
      this.allcontacts.reverse();
      this.contacts = this.allcontacts.slice((this.pageNumber - 1) * 10, this.allcontacts.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allcontacts.length);
      for (let i=0;i<10;i++){
        this.checkboxes[i].checked=false;
      }
    }
    )
}


  deleteContact(){
    for(let i = 9; i>=0; i--){
      if(!this.checkboxes[i].checked)
        continue;
      this.api.deleteContact(this.allcontacts.length-((this.pageNumber-1)*10+i)-1).subscribe();
    }

    this.load();
  }

  on(index : number) {
    this.myDisp.nativeElement.style.display = 'block';
    this.name=this.contacts[index].getName();
    this.addressList=this.contacts[index].getAddressList();
    console.log(this.name);
    console.log(typeof( this.addressList));
  }
  off(){
    this.myDisp.nativeElement.style.display = 'none';
  }

  toggleCheckboxes() {
    this.checkboxes.forEach(checkbox => {
      checkbox.checked = this.selectAll;
    });
  }

  next(){
    this.pageNumber += 1;
    this.contacts = this.allcontacts.slice((this.pageNumber - 1) * 10, this.allcontacts.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allcontacts.length);
  }

  prev(){
    this.pageNumber -= 1;
    this.contacts = this.allcontacts.slice((this.pageNumber - 1) * 10, this.allcontacts.length - (this.pageNumber - 1) * 10 > 10 ? this.pageNumber * 10 : this.allcontacts.length);
  }
 

}
