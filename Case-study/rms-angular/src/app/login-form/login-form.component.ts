import { Component, OnInit } from '@angular/core';
import {AuthenticationService, User} from './authentication.service';
import { Router } from '@angular/router';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';

import {MatSnackBar,MatSnackBarVerticalPosition,MatSnackBarHorizontalPosition} from '@angular/material/snack-bar';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { error } from 'util';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  invalidCredentials = true
  username = ''
  password = ''
  invalidLogin = false
  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'top';
  loginFailed=false;
  constructor(private router : Router,private loginservice: AuthenticationService,private snackBar: MatSnackBar) { 

  }

  ngOnInit() {
  }

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

openSnackBar( message,status){
  this.snackBar.open(message, status, {
    duration: 2000,
    horizontalPosition : this.horizontalPosition,
    verticalPosition : this.verticalPosition,
    panelClass: ['blue-snackbar']
  });
  
}


 checkLogin() {

  this.loginservice.authenticateUsingJwt(this.username,this.password).subscribe( data=>{
    console.log(data);
    this.getUser();
  },error =>{
    this.loginFailed=true;
  });

  
  
  }

  getUser(){
    (this.loginservice.authenticate(this.username, this.password).subscribe(
      data => {
        console.log("-S-S-")
       console.log(data.userRole)
  
        if(data.userRole === 'ROLE_BORROWER'){
          this.router.navigate(['borrower-home'])
          this.openSnackBar('Borrower Login','Success');
          console.log("Navigate to borrower Home")     
        }else if (data.userRole === 'ROLE_LENDER'){
          this.router.navigate(['lender-home'])
          this.openSnackBar('Lender Login','Success');
          console.log("Navigate to lender Home") 
        }
        else if  (data.userRole === 'ROLE_FINANCIAL_ANALYST'){
          this.router.navigate(['financial-analyst-home'])
          this.openSnackBar('Financial Analyst Login','Success');
          console.log("Navigate to financial Home"); 
        }
        else if (data.userRole === 'ROLE_ADMIN'){
          this.router.navigate(['admin-home'])
          this.openSnackBar('Admin Login','Success');
          console.log("Navigate to Admin Home") 
        }else {
          console.log("Invalid credentials .. ")
        }
  
       
        this.invalidLogin = false
        console.log("Invalid Login --")
        console.log(this.invalidLogin)
      },
      error => {
        this.invalidLogin = true
        this.loginFailed=true;
  //     if(error.status === 401) this.openSnackBar('Login','Failed');
      }
      
    )
    );

  }
  


  loginUser(e) {
    e.preventDefault();
  	console.log(e);
  	 this.username = e.target.elements[0].value;
  	 this.password = e.target.elements[1].value;
    this.checkLogin();
    console.log("Logging Process done")
    if(this.invalidCredentials){ 
      console.log("Invalid Credentials")
    }else {
      console.log("Loggin Success")
    } 
   
  }


}