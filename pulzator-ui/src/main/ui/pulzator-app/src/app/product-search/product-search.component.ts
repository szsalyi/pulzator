import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Product} from "../product/product";
import {ProductService} from "../product.service";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";


@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {
  products$: Observable<Product[]>;
  private searcTerms = new Subject<string>();

  constructor(private productService: ProductService) { }

  search(term: string): void {
    this.searcTerms.next(term);
  }

  ngOnInit() {
    this.products$ = this.searcTerms.pipe(
      debounceTime(300),

      distinctUntilChanged(),

      switchMap((term: string) => this.productService.searchProduct(term))
    );
  }

}
