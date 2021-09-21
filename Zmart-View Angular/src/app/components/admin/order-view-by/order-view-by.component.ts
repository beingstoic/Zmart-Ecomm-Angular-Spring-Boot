import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { Order } from 'src/app/models/Order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-view-by',
  templateUrl: './order-view-by.component.html',
  styleUrls: ['./order-view-by.component.css']
})
export class OrderViewByComponent implements OnInit {

  displayedColumns: string[] = ['orderId', 'orderDate', 'amount', 'paymentType','action'];
  dataSource ;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;



  loading:boolean = false;
  order:Order;
  orderList:Order[]=[];
  submitted:boolean = false;
  form1:FormGroup;
  form2:FormGroup;
  form3:FormGroup;
  form4:FormGroup;
  success:boolean = false;
  fail:boolean = false;
  formSubmitted:number=0;
  response: ErrorResponse = new ErrorResponse();
  constructor(private fb:FormBuilder,
              private router:Router,
              private orderService:OrderService) {


     }

  ngOnInit(): void {

    this.form1=this.fb.group({
      userId:['',[Validators.required, Validators.maxLength(5)]]
    });
    this.form2=new FormGroup({
      orderId:new FormControl('',[Validators.required,Validators.maxLength(5)])
    });
    this.form3=this.fb.group({
      orderDate:['',[Validators.required]]
    });
    this.form4=this.fb.group({
      status:['',[Validators.required]]
    });
  }
  get f1(){
    return this.form1.controls;
  }
  get f2(){
    return this.form2.controls;
  }
  get f3(){
    return this.form3.controls;
  }
  get f4(){
    return this.form4.controls;
  }
 

  getByUserId(){
    this.formSubmitted=1;
    this.orderList=[];
    this.response={status:false,message:''};
    this.submitted=true;
    console.log(this.form1.valid);
    if(!this.form1.valid)
    {console.log( this.form1.controls.userId.hasError('maxlength')); 
      return;
      
    }
    this.loading=true;
    this.orderService.getOrdersByUserId(Number(this.f1.userId.value)).subscribe(
      data=>{
        this.loading=false;
        if(data.length>0){
          data.forEach((value)=>{this.orderList.push(value);})
          this.dataSource = new MatTableDataSource(this.orderList);
        }
        else{
          this.response={status:true,message:"No orders found for this user id "};
        }
      },(error)=>{
        this.loading=false;
        console.log(error.error);
        this.response={status:true,message:error.error};
      }
    );

  }
  getByStatus(){
    this.formSubmitted=4;
     this.submitted=true;
     this.orderList=[];
     this.response={status:false,message:''};
     if(!this.form4.valid)
     { console.log("invalid status form")
       return;
     }
     this.loading=true;
     this.orderService.getOrdersByOrderStatus(this.f4.status.value).subscribe(
       data=>{
         this.loading=false;
         if(data.length>0){
           data.forEach((value)=>{this.orderList.push(value);})
           this.dataSource = new MatTableDataSource(this.orderList);
         }
         else{
           this.response={status:true,message:"No orders found with this status "};
         }
       },(error)=>{
         this.loading=false;
         console.log(error.error);
         this.response={status:true,message:error.error};
       }
     );

      
  }
  getByOrderId(){
    this.formSubmitted=2;
    this.submitted=true;
    this.orderList=[];
    this.response={status:false,message:''};
    console.log(this.form2.valid);
    if(!this.form2.valid)
    {
      return;
    }
    this.loading=true;
      this.orderService.getOrderByOrderId(Number(this.form2.controls.orderId.value)).subscribe(
        
        (data)=>{
          this.loading=false;
          if(data!=null){
            console.log(data);
            this.orderList.push(data);
            this.dataSource = new MatTableDataSource(this.orderList);
          }
        },(error)=>{
          this.loading=false;
          console.log(error.error);
          this.response={status:true,message:error.error};
        });
      
     


  }
 
  getByDate(){
    this.formSubmitted=3;
    this.submitted=true;
    this.orderList=[];
    this.response={status:false,message:''};
    if(!this.form3.valid){
         return;
    }
    this.loading=true;
    this.orderService.getOrdersByOrderDate(this.form3.controls.orderDate.value).subscribe(
      (data)=>{
        this.loading=false;
        if(data.length>0){
          data.forEach((value)=>{this.orderList.push(value);})
          this.dataSource = new MatTableDataSource(this.orderList);
        }else{
          this.response={status:true,message:"No orders found for this date"};
        }
      },(error)=>{
        this.loading=false;
        console.log(error.error);
        this.response={status:true,message:error.error};
      });
    
  }

  viewOrderDetails(index:number){
    console.log(index);
      this.router.navigate(['/order',this.orderList[index].orderId]);
  }

  orderDetails(id){
    this.router.navigate(['/order',id]);
  }
}
