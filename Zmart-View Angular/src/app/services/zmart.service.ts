import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { CartModel } from '../models/CartModel';
import { CartItemModel } from '../models/CartItemModel';


@Injectable({
  providedIn: 'root'
})
export class ZmartService {

  constructor(private http:HttpClient) { }

  baseUrl:string="http://localhost:9000/cart/";
  
  public viewCart(userId:number):Observable<CartModel>{
    return this.http.get<CartModel>(this.baseUrl+"viewcart/"+userId);
  }

  public removeItem(userId:number, productId:number){
    return this.http.delete(this.baseUrl+"removeitem/"+ userId+"/" +productId)
  }

  public changeQuantity(userId:number, productId:number, quantity:number):Observable<CartModel>{
    return this.http.put<CartModel>(this.baseUrl + "changequantity/"+ userId + "/"+productId ,quantity);
  }

  public checkOut(userId:number):Observable<CartModel>{
    return this.http.get<CartModel>(this.baseUrl+"checkout/"+userId);
  }

  public registerCart(userId:number){
    console.log("userId:"+ userId);
    return this.http.get(this.baseUrl+"registercart/"+ userId);
  }

  public addItem(userId:number,cartItem:CartItemModel):Observable<CartItemModel>{
    return this.http.put<CartItemModel>(this.baseUrl+"additem/"+userId,cartItem);
  }

}
