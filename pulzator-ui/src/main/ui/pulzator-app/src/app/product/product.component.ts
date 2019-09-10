import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { Product } from "./product";
import { ProductService } from "../product.service";
=======
>>>>>>> master

import { Product } from "./product";
import { ProductService } from "../product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];

<<<<<<< HEAD
  /*selectedProduct: Product;
=======
 /* selectedProduct: Product;
>>>>>>> master
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
    let newProduct = new Product(name, Number.parseInt(quantity) ,(enabled =="true") , Number.parseInt(price));
    if (!newProduct) { return; }
    this.productService.addProduct(newProduct)
      .subscribe(p => this.products.push(p));
  }
}
