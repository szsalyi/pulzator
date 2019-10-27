import { Component, OnInit } from '@angular/core';
import { Product } from "../product/product";
import { ProductService } from "../product.service";
import {CategoryService} from "../category.service";
import {Category} from "../categories/category";
import {Router} from "@angular/router";
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];

  constructor(private router: Router,
              private productService: ProductService,
              private categoryService: CategoryService,
              private authService: AuthenticationService) {}

  ngOnInit() {
    this.getProducts();
    this.getCategories();
  }

  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }

  getCategories(): void {
    this.categoryService.getCategories()
      .subscribe(categories => this.categories = categories)
  }

  logout(): void {
    this.authService.logout();
  }
}
