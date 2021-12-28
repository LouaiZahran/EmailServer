import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Email } from '../mail/email';

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

  get(path: string): Observable<Array<Email>> {
    return this.http.get<Array<Email>>(`${environment.api_url}${path}`, httpOptions);
  }
}