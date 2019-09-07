import { Injectable } from '@angular/core';
import {Product} from "./product/product";
import { PRODUCTS} from "./mock-products";
import {Observable, of} from "rxjs";
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
}
