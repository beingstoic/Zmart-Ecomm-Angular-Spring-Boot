import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id:number=0;
  product: Product = new Product;
  constructor(private route:ActivatedRoute, private router:Router,private aService:AdminService) { }

  ngOnInit() {
    this.product=new Product();
    this.id=this.route.snapshot.params['id'];
    this.aService.fetchProductById(this.id)
    .subscribe(data=>{
      console.log(data)
      this.product=data;
    }, error=>console.log(error)
    );
  }

  updateProduct() {
    this.aService.updateProduct(this.id, this.product)
      .subscribe(data => {
        console.log(data);
        this.product = new Product();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateProduct();    
  }

  gotoList() {
    this.router.navigate(['/products']);
  }

}
