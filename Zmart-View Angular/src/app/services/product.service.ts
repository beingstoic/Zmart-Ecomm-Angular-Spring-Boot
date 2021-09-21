import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:7000/product/showAll");
  }

  getProductByCategory(categoryName:string):Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:7000/product/under/"+categoryName);
  }
}
