import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterModel } from 'src/app/models/register-model';
import { AuthService } from 'src/app/services/auth.service';
import { ZmartService } from 'src/app/services/zmart.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  genders:string[]=['MALE', 'FEMALE', "NOT TO BE SAID"]
  model: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router, private cartService:ZmartService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.register(this.model).subscribe(
      data => {
        console.log(data);
        console.log(this.model)
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.cartService.registerCart(data.userId).subscribe((cart)=>{
            console.log(cart);
        });
        
        this.router.navigateByUrl('/login');
        

      },
      err => {
        this.errorMessage = err.error.message;
        console.log(this.model)
        this.isSignUpFailed = true;
      }
    );
    
  }

}
