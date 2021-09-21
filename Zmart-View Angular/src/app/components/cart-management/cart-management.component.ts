import { Component, OnInit } from '@angular/core';
import { CartItemModel } from 'src/app/models/CartItemModel';
import { ZmartService } from 'src/app/services/zmart.service';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-cart-management',
  templateUrl: './cart-management.component.html',
  styleUrls: ['./cart-management.component.css']
})
export class CartManagementComponent implements OnInit {

  cartItems:CartItemModel[];
  total=0;
  id:number=Number(localStorage.getItem('id'));
  tax:number =0;
  discount =0;
 
  discountedPrice =0;
  
  constructor(private service:ZmartService,
    private router: Router,
    private orderService: OrderService) {
      
   }

  ngOnInit(): void {
    this.service.viewCart(this.id).subscribe(
      (data)=>{
        this.cartItems=data.cartItem;
        console.log(this.cartItems);
      }
      )
  }

  removeItem(productId:number){
    this.service.removeItem(this.id,productId).subscribe(
      (success)=>{
        this.cartItems = this.cartItems.filter(e=>e.productId != productId);
      }
    )
  }
  ngAfterContentChecked() {
    this.total = this.cartItems.reduce(
        (prev, cur) => prev + cur.quantity * cur.price, 0);
        if(this.total>2000){
          this.discountedPrice = (this.total/100)*95;
          this.discount = (this.total/100)*10;
          this.discount = Math.ceil(this.discount);
        }else{
          this.discountedPrice = (this.total/100)*105;
        }
        this.discountedPrice = Math.ceil(this.discountedPrice);
        this.tax = (this.total/100)*5;
        this.tax = Math.ceil(this.tax);
        
  }

  addOne(cartItem:CartItemModel){
    cartItem.quantity++;
    CartManagementComponent.validateCount(cartItem);
    this.service.changeQuantity(this.id,cartItem.productId,cartItem.quantity);
  }
  minusOne(cartItem:CartItemModel){
    cartItem.quantity--;
    CartManagementComponent.validateCount(cartItem);
    this.service.changeQuantity(this.id,cartItem.productId,cartItem.quantity);
   
  }

  static validateCount(cartItem:CartItemModel) {
    const max = 3;
    if (cartItem.quantity > max) {
        cartItem.quantity = max;
    } else if (cartItem.quantity < 1) {
        cartItem.quantity = 1;
    }
    console.log(cartItem.quantity);
  }

  checkOut(){
    console.log('in checkout')

    this.total = this.discountedPrice;
    
    this.service.checkOut(this.id).subscribe((data)=>{
      this.cartItems = data.cartItem;
    }
    );

    this.orderService.setOrder(this.cartItems,this.discountedPrice);
    
    this.router.navigate(['/placeOrder']);
  }

}
