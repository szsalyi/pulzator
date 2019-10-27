import { Injectable } from '@angular/core';

import { Observable, of } from "rxjs";

import { Product } from "./product/product";

import { catchError, map, tap } from "rxjs/operators";

import { MessageService } from "./message.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = 'http://localhost:8080/api/products';

  private headers = { headers: new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()})};

  constructor(
    private http: HttpClient,
    private authService: AuthenticationService,
    private messageService: MessageService
  ) { }

  getProducts(): Observable<Product[]> {
    this.messageService.add("ProductService: fetch product");
    return this.http.get<Product[]>(this.productUrl, this.headers)
      .pipe(
        tap(_ => this.log('Fetched product')),
        catchError(this.handleError<Product[]>('getPorducts', []))
      );
  }

  getProduct(id: number): Observable<Product> {
    this.messageService.add("ProductService: retrieve product by id");
    this.messageService.add("ProductService: fetch product by id=${id}");
/*
    return of(PRODUCTS.find(product => product.id === id));
*/
    const url = `${this.productUrl}/${id}`;
    return this.http.get<Product>(url, this.headers)
      .pipe(
        tap(_ => this.log(`fetched product id=${id}`)),
      catchError(this.handleError<Product>(`getProduct id=${id}`))
    )
  }

  updateProduct(product: Product): Observable<any> {
    this.messageService.add(`ProductService update product id=${product.id} name=${product.name}`);

    return this.http.post(this.productUrl, product, this.headers).pipe(
      tap(_ => this.log(`update product id=${product.id} name=${product.name}`),
      catchError(this.handleError<any>(`updateProduct`)))
    );
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post(this.productUrl, product, this.headers).pipe(
      tap((newPorduct: Product) => this.log(`added product w/ id=${newPorduct.id} and name=${newPorduct.name} product measure id=${newPorduct.productMeasure} product category=${newPorduct.category.id}`)),
      catchError(this.handleError<Product>(`oldProduct category`))
    );
  }

  deleteProduct(product: Product): Observable<Product> {
    const id = typeof product === 'number' ? product : product.id;
    const url = `${this.productUrl}/${id}`;

    return this.http.delete<Product>(url, this.headers).pipe(
      tap(_ => this.log(`deleted product id=${id}`)),
      catchError(this.handleError<Product>('deleteProduct'))
    );
  }

  searchProduct(term: string): Observable<Product[]> {
    if (!term.trim()) {
      return of([]);
    }

    return this.http.get<Product[]>(`${this.productUrl}/?name=${term}`, this.headers).pipe(
      tap(_ => this.log(`found products matching "${term}"`)),
      catchError(this.handleError<Product[]>('searchProducts', []))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message: string) {
    this.messageService.add(`ProductService: ${message}`);
  }

  /*getCategory(id: number): Observable<Product> {
    // TODO: send message _after_ fetching the product
    this.messageService.add('ProductService: fetched product id = ${id');
    return of(PRODUCTS.find(product => product.id === id));
  }*/
}
