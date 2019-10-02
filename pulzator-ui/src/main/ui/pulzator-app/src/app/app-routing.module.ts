import {Component, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductComponent } from "./product/product.component";
import { DashboardComponent} from "./dashboard/dashboard.component";
import { ProductDetailComponent } from "./product-detail/product-detail.component";
import {CategoriesComponent} from "./categories/categories.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./auth-guard.service";
import {RoleGuardService} from "./role-guard.service";

const routes: Routes =  [
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService]},
  { path: 'products', component: ProductComponent, canActivate: [RoleGuardService], data: { expectedRole: 'ROLE_ADMIN' } },
  { path: 'categories', component: CategoriesComponent, canActivate: [AuthGuardService]},
  { path: 'detail/:id', component: ProductDetailComponent, canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
