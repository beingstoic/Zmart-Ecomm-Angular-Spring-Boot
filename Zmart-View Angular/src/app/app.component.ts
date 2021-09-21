import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'ZMart Shopping website';
  private roles: string[];
  isLoggedIn = false;
  showMerchant = false;
  showCustomer = false;
  username: string;
  firstName:string;
  userId:number;


  constructor(private tokenStorageService: TokenStorageService, private router:Router) { }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.userId = user.userId;
      localStorage.setItem('id',user.userId);
      this.showMerchant = this.roles.includes('ROLE_MERCHANT');
      this.showCustomer = this.roles.includes('ROLE_CUSTOMER');
      this.username = user.username;
      if(this.showCustomer)  this.router.navigateByUrl('/home');
      else  this.router.navigateByUrl('/orderby');

    }
    else  this.router.navigateByUrl('/login');
  }

  logout() {
    this.tokenStorageService.signOut();
    localStorage.clear();
    window.location.reload();
  }

  navigateToWebsite(){
    document.location.href = "https://capgemini.com"
  }
}

