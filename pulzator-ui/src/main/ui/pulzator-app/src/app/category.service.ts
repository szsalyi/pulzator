import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Category} from "./categories/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryUrl = 'http://localhost:8080/api/categories';
  private headers = { headers: new HttpHeaders({ 'Content-Type' : 'application/json'})};
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /*getCategories(): Observable<Product[]> {
    this.messageService.add("ProductService: fetch product");
    return of(PRODUCTS);
  }*/

  getCategories(): Observable<Category[]> {
    this.messageService.add("CategoryService: fetch category");
    return this.http.get<Category[]>(this.categoryUrl, this.headers)
      .pipe(
        tap(_ => this.log('Fetched category')),
        catchError(this.handleError<Category[]>('getCategory', []))
      );
  }

  getCategory(id: number): Observable<Category> {
    this.messageService.add("CategoryService: retrieve Category by id");
    this.messageService.add("CategoryService: fetch Category by id=${id}");

    const url = `${this.categoryUrl}/${id}`;
    return this.http.get<Category>(url, this.headers).pipe(
      tap(_ => this.log(`fetched Category id=${id}`)),
      catchError(this.handleError<Category>(`getCategory id=${id}`))
    )
  }

  updateCategory(category: Category): Observable<any> {
    this.messageService.add(`CategoryService update Category id=${category.id} name=${category.name}`);

    return this.http.post(this.categoryUrl, category, this.httpOptions).pipe(
      tap(_ => this.log(`update category id=${category.id} name=${category.name}`),
        catchError(this.handleError<any>(`updateCategory`)))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    }
  }

  addCategory(category: Category): Observable<Category> {
    console.error("vvvvvvvvvvvv" + category);
    return this.http.post(this.categoryUrl, category, this.httpOptions).pipe(
      tap((newCategory: Category) => this.log(`added Category w/ id=${newCategory.id} and name=${newCategory.name}`)),
      catchError(this.handleError<Category>('addCategory'))
    );
  }

  deleteCategory(category: Category): Observable<Category> {
    const id = typeof category === 'number' ? category : category.id;
    const url = `${this.categoryUrl}/${id}`;

    return this.http.delete<Category>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted Category id=${id}`)),
      catchError(this.handleError<Category>('deleteCategory'))
    );
  }

  searchCategory(term: string): Observable<Category[]> {
    if (!term.trim()) {
      return of([]);
    }

    return this.http.get<Category[]>(`${this.categoryUrl}/?name=${term}`, this.httpOptions).pipe(
      tap(_ => this.log(`found Category matching "${term}"`)),
      catchError(this.handleError<Category[]>('searchCategory', []))
    );
  }

  private log(message: string) {
    this.messageService.add(`CategoryService: ${message}`);
  }
}
