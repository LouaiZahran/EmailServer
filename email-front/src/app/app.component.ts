import { Component ,ViewChild, ElementRef,AfterViewInit } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent{
  title: string = "Mail";
}