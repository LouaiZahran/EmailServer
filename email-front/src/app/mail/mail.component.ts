import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-mail',
  templateUrl: './mail.component.html',
  styleUrls: ['./mail.component.css']
})
export class MailComponent implements OnInit {
  constructor() { }
  ngOnInit(): void {}

  filterAdvanced: boolean = false;
  @ViewChild('filter') myFilter!: ElementRef;
  toggle(){
    this.filterAdvanced = !this.filterAdvanced;
    if(this.filterAdvanced){
      this.myFilter.nativeElement.style.display = 'block';
    }
    else{
      this.myFilter.nativeElement.style.display = 'none';
    }
  }
}
