import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItemModel } from 'src/app/models/CartItemModel';
import { Product } from 'src/app/models/product.model';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { ZmartService } from 'src/app/services/zmart.service';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {

  @Input()
  productItem: Product = new Product;
  id:number=Number(localStorage.getItem('id'));
  cartItem:CartItemModel = new CartItemModel;
  constructor(private router:Router,private cartService:ZmartService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
  }
detailsForCustomer(){
this.router.navigateByUrl("/details/"+this.productItem.id);
}
addItem(){

  if(this.tokenStorageService.getToken()){
    this.cartItem.price = this.productItem.unitPrice;
    this.cartItem.productId = this.productItem.id;
    this.cartItem.productName = this.productItem.productName;
    console.log("image url: ",this.productItem.imageUrl)
    this.cartItem.imgUrl = this.productItem.imageUrl;
    this.cartItem.quantity = 1;
    this.cartService.addItem(this.id,this.cartItem).subscribe((data)=>{
      console.log(data)
      alert("Item Added to cart")
    });
  }else{
    this.router.navigate(['/login'])
  }
  
}

}
