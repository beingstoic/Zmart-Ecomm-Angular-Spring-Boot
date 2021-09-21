import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  fetchAllProducts():Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:7000/product/showAll");
  }

  fetchProductById(id:number):Observable<Product>{
    return this.http.get<Product>("http://localhost:7000/product/"+id);
  }

  createProduct(product:Product):Observable<Product>{
    return this.http.post<Product>("http://localhost:7000/product/add",product);
  }

  updateProduct(id:number,value:any): Observable<Object> {
    return this.http.put("http://localhost:7000/product/update/"+id, value);
  }

  deleteProductById(id:number):Observable<any>{
    return this.http.delete<number>("http://localhost:7000/product/delete/"+id,);
  }
}
