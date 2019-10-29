import { Component, OnInit } from '@angular/core';
import { Product } from "../product/product";
import { ProductService } from "../product.service";
import {CategoryService} from "../category.service";
import {Category} from "../categories/category";
import {Router} from "@angular/router";
import {AuthenticationService} from "../authentication.service";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class DashboardComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];
  c = new Category();
  p = new Product();

  columnsToDisplay = Object.getOwnPropertyNames(this.c);
  innerColumnsToDisplay = Object.getOwnPropertyNames(this.p);
  expandedElement: Category | null;
  innerColumn: Product | null;


  dataSource : Category[];

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
      console.log(this.products.length);
  }

  getCategories(): void {
    this.categoryService.getCategories()
      .subscribe(categories => this.dataSource = categories);

  }

  getDisplayedCategoriesColumns(): string[] {
    return this.columnsToDisplay
      .filter(column => column === 'name');
  }

  getDisplayedProductsColumns(): string[] {
    return this.innerColumnsToDisplay
      .filter(column => column !== 'category');
  }

  decrement(soldQuantity): void {

  }

  logout(): void {
    this.authService.logout();
  }
}

