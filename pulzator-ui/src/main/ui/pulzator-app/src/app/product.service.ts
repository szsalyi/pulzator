import { Injectable } from '@angular/core';
import {Product} from "./product/product";
import { PRODUCTS} from "./mock-products";
import { Observable, of } from "rxjs";
import { MessageService } from "./message.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = 'http://localhost:8080/api/products';
  private headers = { headers: new HttpHeaders({ 'Content-Type' : 'application/json'})};

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /*getProducts(): Observable<Product[]> {
    this.messageService.add("ProductService: fetch product");
    return of(PRODUCTS);
  }*/

  getProducts(): Observable<Product[]> {
    this.messageService.add("ProductService: fetch product");
    return this.http.get<Product[]>(this.productUrl, this.headers);
  }

  getProduct(id: number): Observable<Product> {
    this.messageService.add("ProductService: retrieve product by id");
    this.messageService.add("ProductService: fetch product by id=${id}");
    return of(PRODUCTS.find(product => product.id === id));
  }

  private log(message: string) {
    this.messageService.add('ProductService: ${message}');
  }
}
