import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './add-product/add-product.component';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { CartComponent } from './cart/cart.component';
import { CatalogComponent } from './catalog/catalog.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuradService } from './services/auth-gurad.service';
import { SignupComponent } from './signup/signup.component';


const routes: Routes = [
  {path:'', component: CatalogComponent},
  {path:'product/:id', component: ProductComponent},
  {path:'add-product', component: AddProductComponent, canActivate:[AuthGuradService]},
  {path:'edit-product/:id', component: EditProductComponent, canActivate:[AuthGuradService]},
  {path:'cart', component: CartComponent, canActivate:[AuthGuradService]},
  {path:'order/:id', component: OrderComponent, canActivate:[AuthGuradService]},
  {path:'all-orders', component: AllOrdersComponent, canActivate:[AuthGuradService]},
  {path:'profile', component: ProfileComponent, canActivate:[AuthGuradService]},
  {path:'register', component: SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'logout', component: LogoutComponent, canActivate:[AuthGuradService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents=[CatalogComponent,
                                ProductComponent,
                                AddProductComponent,
                                EditProductComponent,
                                CartComponent,
                                OrderComponent,
                                AllOrdersComponent,
                                ProfileComponent,
                                SignupComponent,
                                LoginComponent,
                                LogoutComponent,
                              ];