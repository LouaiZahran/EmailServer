import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Email } from '../mail/email';
import { Globals } from '../globals/Globals';
import { Contact } from '../mail/contact';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  send(path: string, body: Object): Observable<Email> {
    return this.http.post<Email>(`${environment.api_url}${path}`, JSON.stringify(body), httpOptions);
  }

  getEmails(username: string, folder: string): Observable<Array<Email>>{
    return this.http.get<Array<Email>>(`${environment.api_url}/getEmails`, 
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        "username": username,
        "folder": folder
      }
    });
  }
  sortEmails(username: string, folder: string): Observable<Array<Email>>{
    return this.http.get<Array<Email>>(`${environment.api_url}/sortEmails`, 
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        "username": username,
        "folder": folder
      }
    });
  }
  getContact(username: string): Observable<Array<Contact>>{
    return this.http.get<Array<Contact>>(`${environment.api_url}/getContacts`, 
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        "username": username,
      }
    });
  }
  filterEmails(username:string,folder:string,filter:Array<string>,search:string){
    return this.http.get<Array<Email>>(`${environment.api_url}/filterEmails`, 
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        "username": username,
        "filter":filter,
        "folder":folder,
        "search":search,
      }
    });
  }
  filterContacts(username:string,search:string){
    return this.http.get<Array<Contact>>(`${environment.api_url}/filterContacts`, 
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        "username": username,
        "search":search,
      }
    });
  }

  get(path: string): Observable<Array<Email>> {
    return this.http.get<Array<Email>>(`${environment.api_url}${path}`, httpOptions);
  }

  deleteEmail(index: number,folderName:String): Observable<any>{
    let username=Globals.username;
    return this.http.post<any>(`${environment.api_url}/deleteEmail`, JSON.stringify({index,folderName,username}), httpOptions);
  }
  deleteContact(index: number): Observable<any>{
    let username=Globals.username;
    return this.http.post<any>(`${environment.api_url}/deleteContact`, JSON.stringify({username,index}), httpOptions);
  }
  moveEmail(index: number,oldFolderName:string,newFolderName:string): Observable<any>{
    let username=Globals.username;
    return this.http.post<any>(`${environment.api_url}/moveEmail`, JSON.stringify({oldFolderName,newFolderName,username,index}), httpOptions);
  }
}
