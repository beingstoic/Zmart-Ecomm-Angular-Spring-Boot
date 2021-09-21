import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { Order } from 'src/app/models/Order';
import { OrderService } from 'src/app/services/order.service';



@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {

  orderList: Order[] = []
  loading: boolean = false;
  response: ErrorResponse = new ErrorResponse();
  success: boolean = false;
  fail:boolean = false;

  constructor(private orderService: OrderService, private router: Router) { 
    this.getOrderList();
  }

  ngOnInit(): void {
  }

  getOrderList(){
    this.loading = true;
    this.orderService.getOrdersByUserId(parseInt(localStorage.getItem('id'))).subscribe(value => {
      console.log(value);
      this.loading = false;
    
      if (!(value.length==0)){
        this.orderList = value;
      }else{
        this.response={status:true,message:"No orders Found"};
      }
    },(error) => {
      this.loading=false;
       this.response={status:true,message:error.error};
      console.log(error.error);
    })
  }
  viewOrder(id:number)
  { 
   this.router.navigate(['/orderConfirm',id]);
  }
}
