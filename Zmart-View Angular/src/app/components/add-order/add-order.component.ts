import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { Order } from 'src/app/models/Order';
import { OrderService } from 'src/app/services/order.service';


@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
 
  discount:number=0;
  tax:number=0
  total:number=0;
  loading: boolean = false;
  response: ErrorResponse = new ErrorResponse();
  order:Order;
  details:FormGroup;
  submitted:boolean=false;
  success:boolean = false;
  fail:boolean = false;
//---------------------------------------------------------------------------
  
  //----------------------------------------------------------------------

  constructor(
       private fb:FormBuilder,
       private orderService: OrderService,
       private router: Router) { 

       this.order=this.orderService.getOrder();
    }

  ngOnInit(): void {
    this.details=this.fb.group({
      address:['',[Validators.required]],
      contact:['',[Validators.required, Validators.pattern('[7-9]{1}[0-9]{9}')]],
      name:['',[Validators.required,Validators.minLength(2),Validators.maxLength(15),Validators.pattern('[a-zA-Z]{1,}')]]
    });
      this.order=this.orderService.getOrder();
      this.discount=this.orderService.discount;
      this.tax=this.orderService.tax;
      this.total=this.orderService.total;

  }

 get f()
 {
   return this.details.controls;
 }
  submitOrder() {
      
      this.submitted=true;
      if(!this.details.valid){
        
        return;
      }
      this.loading = true;
      const userId = Number(localStorage.getItem('id'));
      console.log(userId);
      this.order.userId=userId;  
      this.order.address=this.details.get('address').value;
      this.order.contact=this.details.get('contact').value;
      this.order.name=this.details.get('name').value;
      console.log(this.order);
      this.orderService.order=this.order;
      this.router.navigate(['/payment'])
    /*  this.orderService.placeOrder(this.order).subscribe(value=> {this.order=value; 
        this.loading=false;
        this.router.navigate(['/orderConfirm',value.orderId])
      },(error)=>{
        console.log(error.error);
        this.loading=false;
        this.response={status:true,message:error.error}
      })*/

     
      
    
  }

  
  
}
