import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Order } from '../models/Order';
import { Status } from '../models/status.enum';
import { CartItemModel } from '../models/CartItemModel';
@Injectable({
  providedIn: 'root'
})
export class OrderService {
  
 
  private bookingUrl=`http://localhost:8021/order`;
   order: Order;
   orderList:Order[]=[];
   total:number=0;
   tax:number=0;
   discount:number;
 //private orderId:number;
  constructor(private http:HttpClient) {
 
   }
 
   placeOrder(order):Observable<any> {
     console.log(order);
     return this.http.post(`${this.bookingUrl}`,order);
   }
   getOrderByOrderId(orderId:number):Observable<any>{
      return this.http.get(`${this.bookingUrl}/orders/orderId/${orderId}`);
   }
 
   getAllOrders():Observable<any> {
 
    return this.http.get(`${this.bookingUrl}/orders`);
   }
 
   getOrdersByUserId(userId:number):Observable<any>{
     return this.http.get(`${this.bookingUrl}/orders/userId/${userId}`)
   }
 
   getOrdersByOrderDate(orderDate:Date):Observable<any>{
     return this.http.get(`${this.bookingUrl}/orders/orderDate/${orderDate}`)
   }
   
   getOrdersByOrderStatus(status: string) : Observable<any>{
    return this.http.get(`${this.bookingUrl}/orders/orderStatus/${status}`)
   }
 
   updateOrder(orderId:number,status:string):Observable<any>{
     return this.http.put(`${this.bookingUrl}/orders/updateOrder/${orderId}/${status}`,{});
   }
   
   setOrder(cartItem:CartItemModel[], discountedPrice:number)
   {   let order={orderId:null,
                  userId:null,
                  amount:null,
                  orderDate:null,
                  deliveryDate:null,
                  orderItems:[],
                  status:null,
                  paymentType:null,
                  address:null,
                  contact:null,
                  name:null,
                  paymentId:null,
                };
        let amount=0;
        this.order=order;
     
       for(let i=0;i<cartItem.length;i++){
        let value=cartItem[i];
            let item={id:null,
              productId:value.productId,
              price:value.price,
              quantity:value.quantity,
              productName:value.productName,
              imageUrl:value.imgUrl
            };
              amount=amount+value.quantity*value.price;
              this.order.orderItems.push(item);
       }
       this.total=amount;
       this.tax=Math.ceil(amount*5/100)
       if(amount>2000)
       {  this.discount=Math.ceil(amount*10/100);
      }
       this.order.amount = discountedPrice;
       this.order.userId=Number(localStorage.getItem('id'));
     
      }
 
   getOrder(): Order{
     return this.order;
   }
}