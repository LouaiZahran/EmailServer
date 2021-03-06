import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Email } from '../email';
@Component({
  selector: 'app-folders',
  templateUrl: './folders.component.html',
  styleUrls: ['./folders.component.css']
})
export class FoldersComponent implements OnInit {
  @ViewChild('myDisplay') myDisp!: ElementRef;
  name:string="New Folder";
  static paste = false;
  static emails:Email[] = [];
  folders: any[] = [
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] },
    {name: 'New Folder', visible: false, emails: [] }
  ]
  isDeleting: boolean = false;
  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  newFolder(name: string){
    for (let index = 0; index < this.folders.length; index++) {
      if (!this.folders[index].visible){
        this.folders[index].visible = true;
        this.folders[index].name = name;
        break;
      }
    }
  }
  delete(){
    this.isDeleting = !this.isDeleting;
  }

  action(index: number){
    if(this.isDeleting){
      this.folders[index].emails = [];
      this.folders[index].visible = false;
    }
    else if(FoldersComponent.paste){
      FoldersComponent.paste = false;
      this.folders[index].emails = FoldersComponent.emails;
    //  GetEmails.folderEmails = this.folders[index].emails;
      this.router.navigate(['/mail/folder']);
    }
    else {
      //GetEmails.folderEmails = this.folders[index].emails;
      this.router.navigate(['/mail/folder']);
    }
  }

  on() {
    this.name = "New Folder";
    this.myDisp.nativeElement.style.display = 'block';

  }
  off(){
    this.newFolder(this.name);
    this.myDisp.nativeElement.style.display = 'none';
  }

  getFolders(){
    return this.folders;
  }
  
}
