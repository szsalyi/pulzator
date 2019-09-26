import {Component, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductComponent } from "./product/product.component";
import { DashboardComponent} from "./dashboard/dashboard.component";
import { ProductDetailComponent } from "./product-detail/product-detail.component";
import {CategoriesComponent} from "./categories/categories.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes =  [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'products', component: ProductComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'detail/:id', component: ProductDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
