import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
<<<<<<< HEAD
import {Location } from "@angular/common";

import { Product} from "../product/product";
import { ProductService } from "../product.service";
import {MessageService} from "../message.service";
=======
import { Location } from "@angular/common";

import { Product} from "../product/product";
import { ProductService } from "../product.service";
>>>>>>> master

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  /*@Input()*/ product: Product;
<<<<<<< HEAD

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location,
    private messageService: MessageService
    ) {}

=======

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location
  ) { }
>>>>>>> master

  ngOnInit() {
    this.getProduct();
  }

  getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
<<<<<<< HEAD
    this.messageService.add('Fetch product ${id} in detail component!');
    this.productService.getProduct(id)
      .subscribe(p => this.product = p);
=======
    this.productService.getProduct(id)
      .subscribe(product => this.product = product);
  }

  goBack(): void {
    this.location.back();
>>>>>>> master
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.productService.updateProduct(this.product)
      .subscribe(() => this.goBack());
  }
}
