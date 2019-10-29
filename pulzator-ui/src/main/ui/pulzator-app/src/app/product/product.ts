import {ProductMeasure} from "./product_measure";
import { Category } from "../categories/category";

export class Product {
  id: number;
  name: string;
  quantity: number;
  enabled: boolean;
  productMeasure: ProductMeasure;
  price: number;
  category: Category;

  constructor();
  constructor(name: string, quantity: number,enabled: boolean, price: number, category: Category, productMeasure?: ProductMeasure)
  constructor(name?: string, quantity?: number,enabled?: boolean, price?: number, category?: Category, productMeasure?: ProductMeasure) {
    this.name = name;
    this.quantity = quantity;
    this.enabled = enabled;
    this.price = price;
    this.productMeasure = productMeasure;
    this.category = category;
  }
}
