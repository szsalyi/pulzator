import { Component, OnInit } from '@angular/core';
import {ProductService} from "../product.service";
import {Category} from "./category";
import {Product} from "../product/product";
import {ProductMeasure} from "../product/product_measure";
import {CategoryService} from "../category.service";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories: Category[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories(): void {
    this.categoryService.getCategories()
      .subscribe(categories => this.categories = categories);
  }

  add(name: string): void {
    let newCategory = new Category(name);

    this.categoryService.addCategory(newCategory)
      .subscribe(c => this.categories.push(c));
  }

  delete(category: Category): void {
    this.categories = this.categories.filter(p => p !== category);
    this.categoryService.deleteCategory(category).subscribe();
  }

}
