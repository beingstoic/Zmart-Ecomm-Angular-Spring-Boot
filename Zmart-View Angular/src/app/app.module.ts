import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import {  FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from  './material/material.module';

import { NgbDropdownConfig } from '@ng-bootstrap/ng-bootstrap';
import{MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card'

import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { MerchantComponent } from './pages/merchant/merchant.component';
import { CustomerComponent } from './pages/customer/customer.component';
import { ConfirmOrderComponent } from './components/confirm-order/confirm-order.component';
import { ViewOrdersComponent } from './components/view-orders/view-orders.component';
import { AddOrderComponent } from './components/add-order/add-order.component';
import { CartManagementComponent } from './components/cart-management/cart-management.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomepageComponent } from './components/homepage/homepage.component';
import { NavBarComponent } from './components/shared/nav-bar/nav-bar.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { ProductListComponent } from './components/homepage/product-list/product-list.component';
import { ProductItemComponent } from './components/homepage/product-list/product-item/product-item.component';
import { FiltersComponent } from './components/homepage/filters/filters.component';
import { CreateProductComponent } from './components/create-product/create-product.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ListofProductsComponent } from './components/listof-products/listof-products.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { OrderDetailsComponent } from './components/admin/order-details/order-details.component';
import { UpdateOrderComponent } from './components/admin/update-order/update-order.component';
import { OrderViewByComponent } from './components/admin/order-view-by/order-view-by.component';
import { PaymentComponent } from './components/payment/payment.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    UserRegistrationComponent,
    MerchantComponent,
    CustomerComponent,
    ConfirmOrderComponent,
    ViewOrdersComponent,
    AddOrderComponent,
    CartManagementComponent,
    AppComponent,
    HomepageComponent,
    NavBarComponent,
    HeaderComponent,
    FooterComponent,
    ProductListComponent,
    ProductItemComponent,
    FiltersComponent,
    CreateProductComponent,
    ProductDetailsComponent,
    ListofProductsComponent,
    UpdateProductComponent,
    OrderDetailsComponent,
    OrderViewByComponent,
    UpdateOrderComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    BrowserModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatCardModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
