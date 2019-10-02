import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from "@angular/router";
import {AuthenticationService} from "./authentication.service";
import  decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate{

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean  {
    const expectedRole = route.data.expectedRole;
    const token = this.authService.getToken();
    const tokenPayload = decode(token);
    console.log(tokenPayload);
    console.log(expectedRole);

    const rolePresented = tokenPayload.role.find(role => role.authority === expectedRole);
    console.log("feeeee" + JSON.stringify(rolePresented));

    if (!this.authService.authenticated() || !rolePresented) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
