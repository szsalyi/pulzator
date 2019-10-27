import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {User} from "./User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private newUser: User;
  private confirmPassword: string;
  private registrationSuccess: boolean;

  constructor(private userService: UserService,
              private authService: AuthenticationService) { }

  ngOnInit() {
  }

  registration(username: string, firstname: string, lastName: string, password: string, email: string): void {
    let roles = ['ROLE_USER'];
    this.newUser = new User(username, firstname, lastName, password, email, true, roles);
    this.userService.registration(this.newUser)
      .subscribe(user => user !== null ? this.registrationSuccess = true: this.registrationSuccess = false);
  }

  userExists(username: string): boolean {
    return false;
  }
}
