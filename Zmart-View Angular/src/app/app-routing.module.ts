import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { from } from 'rxjs';
import { LoginComponent } from './pages/login/login.component';
import {AdminComponent} from './pages/admin/admin.component';
import { MerchantGuard } from './_helper/merchant.guard';
import { CustomerGuard } from './_helper/customer.guard';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { MerchantComponent } from './pages/merchant/merchant.component';
import { CustomerComponent } from './pages/customer/customer.component';
import { ConfirmOrderComponent } from './components/confirm-order/confirm-order.component';
import { ViewOrdersComponent } from './components/view-orders/view-orders.component';
import { CartManagementComponent } from './components/cart-management/cart-management.component';
import { AddOrderComponent } from './components/add-order/add-order.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ListofProductsComponent } from './components/listof-products/listof-products.component';
import { CreateProductComponent } from './components/create-product/create-product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { OrderDetailsComponent } from './components/admin/order-details/order-details.component';
import { OrderViewByComponent } from './components/admin/order-view-by/order-view-by.component';
import { UpdateOrderComponent } from './components/admin/update-order/update-order.component';
import { PaymentComponent } from './components/payment/payment.component';
const routes: Routes = [
  
  { path: 'login', component: LoginComponent },

  {
    path:'home', component: HomepageComponent,
    canActivate: [CustomerGuard]
  },

  { 
    path: 'products', component: ListofProductsComponent,
    canActivate: [MerchantGuard] 
  },
  { 
    path: 'add', component: CreateProductComponent,
    canActivate: [MerchantGuard] 
  },

  { 
    path: 'update/:id', component: UpdateProductComponent,
    canActivate: [MerchantGuard] 
  },
  { 
    path: 'details/:id', component: ProductDetailsComponent,
    canActivate: [CustomerGuard] 
  },

  { 
    path: '', redirectTo: 'home', pathMatch: 'full' 
  },

  {
    path:'register', component:UserRegistrationComponent
  },

  {
    path:'merchant',
    component:MerchantComponent,
    canActivate: [MerchantGuard]
 },

  {
    path:'customer',
    component:CustomerComponent,
    canActivate: [CustomerGuard]
  },

  {
    path: 'orderConfirm/:id', component: ConfirmOrderComponent,
    canActivate: [CustomerGuard]
  },
  {
    path: 'orders', component:ViewOrdersComponent,
    canActivate: [CustomerGuard]
  },
  {
    path:'cart',component:CartManagementComponent,
    canActivate: [CustomerGuard]
  },
  {
    path:'placeOrder',component:AddOrderComponent,
    canActivate:[CustomerGuard]
  },
  {
    path:'order/:id', component:OrderDetailsComponent,
    canActivate: [MerchantGuard]
  },
    {path: 'orderby', component:OrderViewByComponent,
    canActivate: [MerchantGuard]
  },
  {
    path: 'updateOrder/:id', component:UpdateOrderComponent,
    canActivate: [MerchantGuard]
  },
  {
    path: 'payment', component:PaymentComponent,canActivate:[CustomerGuard]
  }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      onSameUrlNavigation: 'reload',
    }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule { }
