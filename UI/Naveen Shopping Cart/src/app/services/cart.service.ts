import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CartModel } from '../models/cart-model';
import { ItemModel } from '../models/item-model';



@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCart(userId: number){
    return this.http.get<CartModel>(`http://localhost:8050/cart/${userId}`);
  }

  getItems(userId: number){
    return this.http.get<ItemModel[]>(`http://localhost:8050/cart/items/${userId}`);
  }

  addToCart(userId: number, prodId: number){
    return this.http.put(`http://localhost:8050/cart/add/${userId}/${prodId}`, {});
  }

  removeFromCart(userId: number, prodId: number){
    return this.http.put(`http://localhost:8050/cart/remove/${userId}/${prodId}`, {});
  }

  clearCart(userId: number){
    return this.http.put(`http://localhost:8050/cart/clear/${userId}`, {})
  }

  cartItems(userId: number){
    return this.http.get<Number>(`http://localhost:8050/cart/cartItems/${userId}`);
  }

  createCart(userId: number){
    return this.http.post(`http://localhost:8050/cart/create/${userId}`, {});
  }

}
