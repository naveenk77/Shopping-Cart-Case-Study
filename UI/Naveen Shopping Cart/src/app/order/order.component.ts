import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';



@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  public userId=-1;
  public orderId=0;
  public order: any;
  public items: any;
  public name='';
  public cartItems=0;
  constructor(public cartService: CartService, public orderService: OrderService, public route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap)=> this.orderId= parseInt(params.get('id') || '0'));
    this.userId= parseInt(sessionStorage.getItem('userId') || '-1');
    this.name= sessionStorage.getItem('name') || '';
    console.log(this.userId);
    
    if(this.orderId==-1){
      this.orderService.getRecentOrder(this.userId).subscribe(data=>{
        this.order=data;
        console.log(this.order);
      });
  
      this.orderService.getRecentItems(this.userId).subscribe(data=>{
        this.items=data;
        console.log(this.items);
      });
    }
    else{
      this.orderService.getOrder(this.orderId).subscribe(data=> this.order=data);
      this.orderService.getItems(this.orderId).subscribe(data=> this.items=data);
    }

    this.cartService.cartItems(this.userId).subscribe(data=> this.cartItems=Number(data));
    
  }
  

}