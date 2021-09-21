import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorResponse } from 'src/app/models/error-response';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {
  form4:FormGroup;
  orderId:number;
  submitted:boolean=false;
  loading:boolean=false;
  fail:boolean=false;
  response: ErrorResponse = new ErrorResponse();
  constructor(private fb:FormBuilder,
    private orderService:OrderService,
    private route: ActivatedRoute,
    private router: Router) {

   }

  ngOnInit(): void {
    this.form4=this.fb.group({
      status:['',[Validators.required]]
    });
    this.route.params.subscribe(params => {
      this.orderId = params['id'];})
  }
  get f4(){
    return this.form4.controls;
  }

  updateStatus(){
    this.submitted=true;
    this.loading=true;
    this.response={status:false,message:''};
    this.orderService.updateOrder(this.orderId,this.form4.controls.status.value).subscribe(
     ( data)=>{
        if(data!=null){
          this.loading=false;
          alert("Order Updated");
          this.router.navigate(['/order',this.orderId]);
        }
      },(error)=>{
        this.loading=false;
        console.log(error.error);
        this.response={status:true,message:error.error}
      }
    )
  }

}
