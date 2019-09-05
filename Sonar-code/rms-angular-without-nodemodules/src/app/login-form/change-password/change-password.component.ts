import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ForgotPasswordService } from './../../service/forgot-password.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  newPassword:string;
  emailId:string;
  notAppliedForForgotPassword=false;
  hintColor='#ff0000';
  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'top';
  constructor(private forgotPasswordService:ForgotPasswordService,private snackBar: MatSnackBar,private router : Router) { }

  ngOnInit() {
    var email= localStorage.getItem('otpEmail');
    this.emailId=email;
  } 

  onSubmit(event){
    this.newPassword=event.target.elements[0].value;
    console.log("password",event.target.elements[0].value);

    var postJson = {
      "emailID" : this.emailId,
      "newPassword" : this.newPassword
    }

    this.forgotPasswordService.setNewPassword(postJson).subscribe(
      data=>{
        console.log(data);
        if(data.status === 200){
          this.notAppliedForForgotPassword=false;
          this.openSnackBar('Password Updated','successfully');
          this.router.navigate(['/login']);
        }else if(data.status === 401){
          this.notAppliedForForgotPassword=true;
        }        
      }
    );
  }
  openSnackBar( message,status){
    this.snackBar.open(message, status, {
      duration: 2000,
      horizontalPosition : this.horizontalPosition,
      verticalPosition : this.verticalPosition,
      panelClass: ['blue-snackbar']
    });
    
  }
}
