import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroupDirective } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ProductModel } from '../models/product-model';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';
import { CatalogService } from '../services/catalog.service';


@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  public admin=false;
  public isLoggedIn= false;
  public name='';
  public products: ProductModel[]=[];
  public cartItems=0;
  public userId=-1;
  constructor(private cartService: CartService, private catalogService: CatalogService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.catalogService.getAllProducts().subscribe(data =>{
      this.products=data
      console.log(this.products)
    });
    this.isLoggedIn= this.authService.isLoggedIn();
    this.name=sessionStorage.getItem('name') || '';
    this.userId=parseInt(sessionStorage.getItem('userId') || '-1');
    if(this.userId==1){
      this.admin=true;
    }
    this.cartService.cartItems(this.userId).subscribe(data=> this.cartItems=Number(data));
    
  }

  delete(product: ProductModel){
    Swal.fire({
        title: 'Are you sure?',
        text: '',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.value) {
          this.catalogService.deleteProduct(product.id).subscribe();
          this.products.forEach((p,i)=>{
            if(p==product)
            this.products.splice(i,1);
          })
            Swal.fire(
                'Removed!',
                'Product has removed.',
                'success'
            )
        } 
    })
}

logout(){
  Swal.fire({
    title: 'Are you sure?',
    text: '',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes',
    cancelButtonText: 'Cancel'
}).then((result) => {
    if (result.value) {
      this.router.navigate(['/logout']);
    } 
})
}

public findByName(){
  this.products=this.products.filter(res =>{
    return res.name.toLocaleLowerCase().match(this.name.toLocaleLowerCase());
    });
}
}


   


