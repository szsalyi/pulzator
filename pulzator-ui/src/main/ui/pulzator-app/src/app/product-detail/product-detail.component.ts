import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import {Location } from "@angular/common";

import { Product} from "../product/product";
import { ProductService } from "../product.service";
import {MessageService} from "../message.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  /*@Input()*/ product: Product;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location,
    private messageService: MessageService
    ) {}


  ngOnInit() {
    this.getProduct();
  }

  getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.messageService.add('Fetch product ${id} in detail component!');
    this.productService.getProduct(id)
      .subscribe(p => this.product = p);
  }

  goBack(): void {
    this.location.back();
  }
}
