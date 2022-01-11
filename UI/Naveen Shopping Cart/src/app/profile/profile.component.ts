import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public userId= -1;
  public user: any;
  public name='';
  public cartItems=0;

  constructor(private cartService: CartService, private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    //this.route.paramMap.subscribe((params: ParamMap)=> this.userId= parseInt(params.get("id") || '-1'));
    this.userId= parseInt(sessionStorage.getItem('userId') || '-1');
    this.userService.getUser(this.userId).subscribe(data=>{
      this.user=data;
      console.log(this.user);
    });
    this.name=sessionStorage.getItem('name') || '';
    this.cartService.cartItems(this.userId).subscribe(data=> this.cartItems=Number(data));
  }

}
