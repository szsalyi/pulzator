import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";
import {AuthRequest} from "./login/AuthRequest";
import {catchError, map, tap} from "rxjs/operators";
import {AuthResponse} from "./login/AuthResponse";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {
  private authUrl = 'http://localhost:8080/auth';
  private logoutUrl = '';
  private headers = { headers: new HttpHeaders({ 'Content-Type' : 'application/json'})};
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private jwtHelper: JwtHelperService,
    private router: Router
  ) { }

  login(authRequest: AuthRequest): Observable<boolean> {
    let token;
    return this.http.post<AuthResponse>(this.authUrl, authRequest, this.httpOptions).pipe(
      map((response: AuthResponse) => {
         if (response.token) {
            token = response.token;
            localStorage.setItem('currentUser', JSON.stringify({username: authRequest.loginIdentifier, token: token}));
            console.log(localStorage.getItem('currentUser'));
            return true;
        } else {
            return false;
        }}),
      catchError(this.handleError<boolean>('login'))
    );
  }

  authenticated(): boolean {
    const token = this.getToken();
    console.log(!this.jwtHelper.isTokenExpired(token));
    return !this.jwtHelper.isTokenExpired(token);
  }

  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login'])
  }

  private log(message: string) {
    this.messageService.add(`CategoryService: ${message}`);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    }
  }
}
