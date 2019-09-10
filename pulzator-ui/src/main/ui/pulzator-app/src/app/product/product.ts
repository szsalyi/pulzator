export class Product {
  id: number;
  name: string;
  quantity: number;
  enabled: boolean;
  created_at: Date;
  updated_at: Date;
  product_measure_id: number;
  price: number;
  category_id: number;

  constructor(name: string, quantity: number,enabled: boolean, price: number) {
    this.name = name;
    this.quantity = quantity;
    this.enabled = enabled;
    this.price = price;
  }
}
