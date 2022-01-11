import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm= this.fb.group({
    username: ['', [Validators.required, Validators.minLength(5)]],
    password: ['', [Validators.required, Validators.minLength(8)]]
  });
  constructor(private fb: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.authenticate(this.loginForm.value.username, this.loginForm.value.password);
  }

}
