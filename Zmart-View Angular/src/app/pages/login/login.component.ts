//Login Component also uses AuthService to work with Observable object.
//It calls TokenStorageService methods to check loggedIn status and save Token, User info to Session Storage.
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { TokenStorageService } from '../../services/token-storage.service';
//import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loading:boolean=false;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router:Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.loading=false;
      this.roles = this.tokenStorage.getUser().roles;
    }
    
  }

  onSubmit() {
    this.authService.login(this.model).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.loading=true;
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage()
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
b
  reloadPage() {
    window.location.reload();
    //this.router.navigateByUrl('/home');

  }
}