import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { Order } from 'src/app/models/Order';
import { OrderService } from 'src/app/services/order.service';
import {Status} from 'src/app/models/status.enum'
@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.css']
})
export class ConfirmOrderComponent implements OnInit {

  
  order: Order;
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
          this.data=true;
        } 
      },(error)=>{
        console.log(error.error);
        this.loading=false;
        this.data=false;
        this.response={status: true, message:error.error}
      })
    })
  }


  cancelOrder() {
    this.loading = true;
      this.orderService.updateOrder(this.order.orderId,"CANCELLED").subscribe(value => {
        this.loading=false;
      if (value==null){
        this.data=false;
        this.response = {status: true, message: "Server error"}
      }else{
        this.order=value;
        this.data=true;
      }
    },(error)=>{
      this.loading=false;
      this.data=false;
      this.response={status: true, message:error.error};
    })
  
  }

  

}
