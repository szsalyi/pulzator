import { Component, OnInit } from '@angular/core';

import { Product } from "./product";
import { ProductService } from "../product.service";
import { ProductMeasure } from "./product_measure";
import { Category } from "../categories/category";
import {CategoryService} from "../category.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];
  categories: Category[];

 /* selectedProduct: Product;
  onSelect(product: Product): void {
    this.selectedProduct = product;
  }*/

  constructor(private productService: ProductService,
              private categoryService: CategoryService) { }

  ngOnInit() {
    this.getProducts();
    this.getCategories();
  }

  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }

  add(name: string, quantity: string, enabled: string, price: string, categoryId: number): void {

    let category = new Category();
    category.id = categoryId;
    let newProduct = new Product(name, Number.parseInt(quantity) ,(enabled == "true") , Number.parseInt(price), category);

    newProduct.productMeasure = new ProductMeasure(1);
    if (!newProduct) { return; }
    this.productService.addProduct(newProduct)
      .subscribe(p => this.products.push(p));
  }

  delete(product: Product): void {
    this.products = this.products.filter(p => p !== product);
    this.productService.deleteProduct(product).subscribe();
  }

  getCategories(): void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }
}
