import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from '../models/Payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  
  private paymentUrl=`http://localhost:8021/payment`;
  payment:Payment;
  paymentList:Payment[]=[];

  constructor(private http:HttpClient) {

  }
  makePayment(payment):Observable<any> {
    console.log(payment);
    return this.http.post(`${this.paymentUrl}`,payment);
  }
  getPaymentById(paymentId):Observable<any> {
    return this.http.get(`${this.paymentUrl}/paymentId/${paymentId}`);
  }
  getPaymentByUserId(userId):Observable<any> {
    return this.http.get(`${this.paymentUrl}/userId/${userId}`);
  }
}
