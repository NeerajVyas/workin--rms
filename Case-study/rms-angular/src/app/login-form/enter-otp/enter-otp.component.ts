import { ForgotPasswordService } from 'src/app/service/forgot-password.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-enter-otp',
  templateUrl: './enter-otp.component.html',
  styleUrls: ['./enter-otp.component.css']
})
export class EnterOtpComponent implements OnInit {
  otp:number;
  enterOTPEmailId:string;
  otpEmailJson:any;
  constructor(private router:Router, private forgotPasswordService : ForgotPasswordService) { }
  emailId:string;
  verificationFailed = false;
  notAppliedForgotPassword=false;
  hintColor='#ff0000';
  ngOnInit() {
 //   this.enterOTPEmailId=this.router.queryParams['getValue']();
 // console.log("In enter OTP .ts", this.enterOTPEmailId);
    var email= localStorage.getItem('otpEmail');
    this.emailId=email;
    
  }

  submitData(events){

    console.log('AYAYAYAYAYAYAY',events);
    
     this.otp=events.target.elements[0].value;
    console.log("enter OTP ts",this.otp);    

    var postJson = {
      "emailID" : this.emailId,
      "otp" : this.otp
    }

    console.log(postJson);
    this.forgotPasswordService.verifyOTP(postJson).subscribe(data=>{
      if(data.status == 404){
        this.verificationFailed=true;
        this.notAppliedForgotPassword=false;
        console.log(data);
        localStorage.clear();
      }if(data.status == 401){
        this.verificationFailed=false;
        this.notAppliedForgotPassword=true;
        console.log(data);
      }
      else if(data.status == 200){
        this.verificationFailed=false;
        this.notAppliedForgotPassword=false;
        console.log(data);
        console.log(data.status);
        this.router.navigate(['/change-password']);
        localStorage.setItem('otpEmail',this.emailId);
      } 
   

    });

/*
    this.otpEmailJson.push({ "otp": this.otp, "emailID": this.emailId });
    console.log(this.otpEmailJson);
    this.forgotPasswordService.verifyOTP(this.otpEmailJson).subscribe(data=>{

      console.log(data); */
 /*      if(data.status == 404){
        this.verificationFailed=true;
        this.notAppliedForgotPassword=false;
      }if(data.status == 401){
        this.verificationFailed=false;
        this.notAppliedForgotPassword=true;
      }
      else if(data.status == 200){
 //       this.verificationFailed=false;
 //       this.notAppliedForgotPassword=false;
 //       console.log(data.status);
   //     localStorage.setItem('otpEmail',this.emailId);
      } 
   

    });
    */
//    console.log(this.otpEmailJson);

  }
 
}
