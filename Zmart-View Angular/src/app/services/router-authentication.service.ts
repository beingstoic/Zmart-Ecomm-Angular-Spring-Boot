import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RouterAuthenticationService {

  constructor(private tokenStorage: TokenStorageService) { }
    public isCustomerAuthenticated(): boolean {
      if(this.tokenStorage.getUser().roles[0] === 'ROLE_CUSTOMER'){
        return true;
      }
      else{
        return false;
      }
  }

  public isMerchantAuthenticated(): boolean {
    
      if(this.tokenStorage.getUser().roles[0] === 'ROLE_MERCHANT'){
        return true;
      }
      else{
        return false;
      }
    
  }

}

 