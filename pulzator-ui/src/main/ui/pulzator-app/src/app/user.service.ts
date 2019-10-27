import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "./registration/User";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {MessageService} from "./message.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private registrationUrl = 'http://localhost:8080/api/users';
  private headers = { headers: new HttpHeaders({
      'Content-Type' : 'application/json'})
  };

  constructor(private http: HttpClient,
              private messageService: MessageService) { }

  registration(user: User): Observable<User> {
    return this.http.post<User>(this.registrationUrl, user, this.headers).pipe(
      catchError(this.handleError<User>('register', user))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message: string) {
    this.messageService.add(`ProductService: ${message}`);
  }
}
