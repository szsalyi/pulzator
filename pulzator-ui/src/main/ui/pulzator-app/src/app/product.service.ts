import { Injectable } from '@angular/core';

import { Observable, of } from "rxjs";

import { Product } from "./product/product";
import { PRODUCTS} from "./mock-products";
import { MessageService } from "./message.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private messageService: MessageService) { }

  getProducts(): Observable<Product[]> {
    this.messageService.add("ProductService: fetch product");
    return of(PRODUCTS);
  }

  getProduct(id: number): Observable<Product> {
    // TODO: send message _after_ fetching the product
    this.messageService.add('ProductService: fetched product id = ${id');
    return of(PRODUCTS.find(product => product.id === id));
  }
}
