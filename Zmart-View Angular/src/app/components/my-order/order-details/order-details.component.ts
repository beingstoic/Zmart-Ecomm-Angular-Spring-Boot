import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

import { Product } from 'src/app/models/product';
import { Order } from 'src/app/models/Order';
import { ErrorResponse } from 'src/app/models/error-response';



@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderId:string;
  order:Order;
  fullName:string;
  dataLoad:boolean=false;
  loading: boolean = false;
  response: ErrorResponse = new ErrorResponse();
  data:boolean=false;
  fail: boolean=false; 
  success:boolean=false;

  constructor(private orderService:OrderService,
              private route: ActivatedRoute) {

   }

  ngOnInit(): void {
    this.getOrderById();
    this.success=false;
    this.fail=false;
  }


  getOrderById() {
    this.loading = true;
   this.data=false;
    this.route.params.subscribe(params => {
      const id: number = params['id'];
      this.orderService.getOrderByOrderId(id).subscribe(value => {
        this.loading = false;
        
        console.log(value);
        if (!(value==null)) {
          this.order=value;
         // this.order=new Order(value);
          this.data=true;
        } else {
          this.fail=true;
          this.success=false;
          this.response = {status: true, message:"This Order does not exist"};
        }
      })
    })
  }


  cancelOrder() {
    this.loading = true;
      this.orderService.updateOrder(this.order.orderId,"CANCELLED").subscribe(value => {
      this.loading = false;
      this.order=value;
      if (value==null){
        this.fail=true;
        this.success=false;
        this.response = {status: true, message: "Server error"}
      }else {
        this.fail=false;
        this.success=true;
        this.response = {status: true, message: "Booking Cancelled"}
      }
    })
  
  }

  downloadOrderReciept() {

  }
}
