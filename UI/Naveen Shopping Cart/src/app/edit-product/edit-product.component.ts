import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { CatalogService } from '../services/catalog.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  name='';
  categories=["Electronics", "Clothing", "Sports"];
  product: any;
  prodId=-1;
  cartItems=0;
  userId=-1;
  productForm: any;

  constructor(public fb: FormBuilder,public cartService: CartService, public catalogService: CatalogService, public router: Router, public route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap)=> this.prodId= parseInt(params.get('id')||'-1'));
    this.name= sessionStorage.getItem('name') || '';
    this.catalogService.getProduct(this.prodId).subscribe(data=>{
      this.productForm= this.fb.group({
        id: [data.id],
        name: [data.name, [Validators.required, Validators.minLength(3)]],
        category: [data.category, [Validators.required]],
        price: [data.price, [Validators.required]],
        imagesource: [data.image, [Validators.required]],
        image: [data.image],
        description: [data.description]
      });
    });
    this.userId=parseInt(sessionStorage.getItem('userId') || '-1');
    this.cartService.cartItems(this.userId).subscribe(data=> this.cartItems=Number(data));
  }

  editProduct(){
    let {imagesource, ...product}= this.productForm.value;
    console.log(product);
    this.catalogService.editProduct(product).subscribe();
    this.router.navigate(['']);
  }

  onFileUpload(event: any){
    const reader = new FileReader();
    
    if(event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
    
      reader.onload = () => {
     
        this.productForm.patchValue({
          image: reader.result
        });
   
      };
   
    }
  }

}
