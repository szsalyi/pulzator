import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Category} from "./categories/category";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryUrl = 'http://localhost:8080/api/categories';
  private commonHeaders = { headers: new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization': 'Bearer ' + this.autService.getToken()}
      )};

  private httpDeleteOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'DELETE',
      'Access-Control-Allow-Headers': 'X-Requested-With,Content-Type',
      'Authorization': 'Bearer ' + this.autService.getToken()}
  )};

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private autService: AuthenticationService
  ) { }

  getCategories(): Observable<Category[]> {
    this.messageService.add("CategoryService: fetch category");
    return this.http.get<Category[]>(this.categoryUrl, this.commonHeaders)
      .pipe(
        tap(_ => this.log('Fetched category')),
        catchError(this.handleError<Category[]>('getCategory', []))
      );
  }

  getCategory(id: number): Observable<Category> {
    this.messageService.add(`CategoryService: fetch Category by id=${id}`);

    const url = `${this.categoryUrl}/${id}`;
    return this.http.get<Category>(url, this.commonHeaders).pipe(
      tap(_ => this.log(`fetched Category id=${id}`)),
      catchError(this.handleError<Category>(`getCategory id=${id}`))
    )
  }

  updateCategory(category: Category): Observable<any> {
    this.messageService.add(`CategoryService update Category id=${category.id} name=${category.name}`);

    return this.http.post(this.categoryUrl, category, this.commonHeaders).pipe(
      tap(_ => this.log(`update category id=${category.id} name=${category.name}`),
        catchError(this.handleError<any>(`updateCategory`)))
    );
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post(this.categoryUrl, category, this.commonHeaders).pipe(
      tap((newCategory: Category) => this.log(`added Category w/ id=${newCategory.id} and name=${newCategory.name}`)),
      catchError(this.handleError<Category>('addCategory'))
    );
  }

  deleteCategory(category: Category): Observable<Category> {
    const id = typeof category === 'number' ? category : category.id;
    const url = `${this.categoryUrl}/${id}`;

    return this.http.delete<Category>(url, this.commonHeaders).pipe(
      tap(_ => this.log(`deleted Category id=${id}`)),
      catchError(this.handleError<Category>('deleteCategory'))
    );
  }

  searchCategory(term: string): Observable<Category[]> {
    if (!term.trim()) {
      return of([]);
    }

    return this.http.get<Category[]>(`${this.categoryUrl}/?name=${term}`, this.commonHeaders).pipe(
      tap(_ => this.log(`found Category matching "${term}"`)),
      catchError(this.handleError<Category[]>('searchCategory', []))
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
    this.messageService.add(`CategoryService: ${message}`);
  }
}
