import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { Order } from 'src/app/models/Order';
import { Payment } from 'src/app/models/Payment';
import { OrderService } from 'src/app/services/order.service';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  cardDetails:FormGroup;
  order:Order;
  loading:boolean = false;
  submitted:boolean = false;
  payment:Payment;
  success:boolean = false;
  successMessage="Payment Successful. Order Confirmed";
  months:string[]=['01','02','03','04','05','06','07','08','09','10','11','12'];
  years:string[]=['21','22','23','24','26','27','28','29','30'];
  response:ErrorResponse=new ErrorResponse();
  constructor( private fb:FormBuilder,
    private orderService: OrderService,
    private paymentService:PaymentService,
    private router: Router) { }

  ngOnInit(): void {
    this.cardDetails=this.fb.group({
      cardNo:['',[Validators.required, Validators.minLength(16),Validators.pattern('[1-9]{1}[0-9]{15}')]],
      cvv:['',[Validators.required,Validators.pattern('[0-9]{3}')]],
      exmonth:['',[Validators.required]],
      exyear:['',[Validators.required]]
    });
      this.order=this.orderService.getOrder();
      this.loading = false;
      this.success = false;
  }
 
  get f1(){
      return this.cardDetails.controls;
  }

  checkCVV(){
    if(this.cardDetails.controls.cvv.value.length()>3)
    { return false;}
    else return true;
  }
  makePayment(){
    this.submitted=true;
  /*  if(!this.cardDetails.valid){
      return;
    }*/
    this.loading=true;
    this.payment={paymentId:null,
                  userId:Number(localStorage.getItem('id')),
                  amount:this.order.amount,
                  paymentStatus:null,
                  paymentTime:null};
                  console.log(this.payment);
    this.paymentService.makePayment(this.payment).subscribe(
      (data)=>{
         
        
        this.order.paymentId=data.paymentId;
        this.order.paymentType="DEBIT CARD"
        this.orderService.placeOrder(this.order).subscribe(value=> {
        this.order=value; 
        this.loading=false;
        this.success=true;
        setTimeout(()=>this.router.navigate(['/orderConfirm',value.orderId]),3000)
       
      },(error)=>{
        console.log(error.error);
        this.loading=false;
        this.response={status:true,message:error.error}
      })
      },
      (error)=>{
        console.log(error.error);
        this.loading=false;
        this.response={status:true,message:error.error}
        setTimeout(()=>{this.router.navigate(['/placeOrder']);},3000)
      }
    );
  }
}
