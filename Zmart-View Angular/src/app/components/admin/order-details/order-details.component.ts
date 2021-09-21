import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

import { Product } from 'src/app/models/product';
import { Order } from 'src/app/models/Order';
import { ErrorResponse } from 'src/app/models/error-response';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';



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
  form4:FormGroup;

  constructor(private orderService:OrderService,
              private router: Router,
              private route: ActivatedRoute,
              private fb:FormBuilder) {

   }

  ngOnInit(): void {
    this.getOrderById();
    this.success=false;
    this.fail=false;
    
  }
 get f4(){
   return this.form4.controls;
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
        } else {
          
          this.response = {status: true, message:"This Order does not exist"};
        }
      },(error)=>{
        this.loading=false;
        console.log(error.error);
        this.response={status:true, message:error.error}
      })
    })
  }


  

  updateStatus(){
    this.router.navigate(['/updateOrder',this.order.orderId])
  }
}
