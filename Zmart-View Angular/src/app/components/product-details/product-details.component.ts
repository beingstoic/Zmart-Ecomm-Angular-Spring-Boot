import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItemModel } from 'src/app/models/CartItemModel';
import { Product } from 'src/app/models/product.model';
import { AdminService } from 'src/app/services/admin.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { ZmartService } from 'src/app/services/zmart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  id: number = 0;
  userId :number=Number(localStorage.getItem('id'));
  product: Product = new Product;
  cartItem: CartItemModel = new CartItemModel;
  
  constructor(private route: ActivatedRoute, private router: Router, private aService: AdminService,
              private cartService:ZmartService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.product = new Product();
    this.id = this.route.snapshot.params['id'];

    this.aService.fetchProductById(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error)
      );
  }

  home() {
    this.router.navigateByUrl('/home');
  }

  // list() {
  //   this.router.navigate(['products']);
  // }
  addItem(){

    if(this.tokenStorageService.getToken()){
      this.cartItem.price = this.product.unitPrice;
      this.cartItem.productId = this.product.id;
      this.cartItem.productName = this.product.productName;
      this.cartItem.quantity = 1;
      this.cartItem.imgUrl=this.product.imageUrl;
      this.cartService.addItem(this.userId,this.cartItem).subscribe((data)=>{
      alert("Item Added to cart")
      console.log(data)
      });
    }else{
      this.router.navigate(['/login']);
    }
  }
}
