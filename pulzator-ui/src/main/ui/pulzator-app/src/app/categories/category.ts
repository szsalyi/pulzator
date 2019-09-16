import {Product} from "../product/product";

export class Category {
  id: number;
  name: string;
  products: Product[];

  constructor(name?: string, products?: Product[]) {
    this.name = name;
    this.products = products;
  }

}
