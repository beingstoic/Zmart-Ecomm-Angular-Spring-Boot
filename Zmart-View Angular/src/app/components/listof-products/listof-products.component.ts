import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from 'src/app/models/product.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-listof-products',
  templateUrl: './listof-products.component.html',
  styleUrls: ['./listof-products.component.css']
})
export class ListofProductsComponent implements OnInit {

  products?:Observable<Product[]>;
  constructor(private aService:AdminService,private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.products=this.aService.fetchAllProducts();
  }

  deleteProduct(id: number) {
    this.aService.deleteProductById(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  updateProduct(id:number){
    this.router.navigate(['update',id])
  }

}
