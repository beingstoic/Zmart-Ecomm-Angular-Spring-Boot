import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';  
import { RouterAuthenticationService } from '../services/router-authentication.service';
import { of } from 'rxjs/internal/observable/of';

@Injectable({
  providedIn: 'root'
})
export class MerchantGuard implements CanActivate {constructor(private routeAuth: RouterAuthenticationService){}
canActivate(
  next: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): Observable<boolean>{

    if(this.routeAuth.isMerchantAuthenticated()){
      return of(true)
    }
    else{
      return of(false)
    }
}

}
