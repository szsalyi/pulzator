import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  canActivate(): boolean {
    console.log("HEEEEEEE");
    if (!this.authService.authenticated()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
