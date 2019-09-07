import { Component, OnInit } from '@angular/core';
import {Product} from "./product";
import {ProductService} from "../product.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];

  selectedProduct: Product;
  onSelect(product: Product): void {
    this.selectedProduct = product;
  }

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }
}
