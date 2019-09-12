import { Component, OnInit } from '@angular/core';

import { Product } from "./product";
import { ProductService } from "../product.service";
import {ProductMeasure} from "./product_measure";
import {Category} from "../categories/category";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];


 /* selectedProduct: Product;
  onSelect(product: Product): void {
    this.selectedProduct = product;
  }*/

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }

  add(name: string,quantity: string,enabled: string,price: string): void {
    let category = new Category(2);

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
}
