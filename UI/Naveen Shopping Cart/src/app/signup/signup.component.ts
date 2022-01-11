import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm= this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    username: ['', [Validators.required, Validators.minLength(5)]],
    email: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    cpassword: ['', [Validators.required]],
    number: ['', [Validators.required, Validators.minLength(10)]],
    gender: ['', Validators.required]
  });

address= this.fb.group({
    house: ['', [Validators.required]],
    street: [''],
    area: ['', [Validators.required]],
    city: ['', [Validators.required]],
    state: ['', [Validators.required]],
    country: ['', [Validators.required]]
  })
  constructor(private fb: FormBuilder,private cartService: CartService, private authService: AuthService, public router: Router) { }

  ngOnInit(): void {
  }

  register(){
    let userId=-1;
    let {cpassword, ...user}= this.signupForm.value;
    user.address=this.address.value;
    this.authService.register(user).subscribe(data=> this.cartService.createCart(Number(data)).subscribe());
    this.router.navigate(['/login']);
  }

}
