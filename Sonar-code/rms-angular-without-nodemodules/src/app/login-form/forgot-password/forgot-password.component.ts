import { ActivatedRoute } from '@angular/router';
import { EnterOtpComponent } from './../enter-otp/enter-otp.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ForgotPasswordService } from 'src/app/service/forgot-password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  emailId:string;
  disable: boolean=true;
  //userEmail:any;
  hintColor='#ff0000';
  emailNotRegistered=false;
  constructor(private router: Router,private forgotPasswordService:ForgotPasswordService) { 
    
  }

  ngOnInit() {
  }


  sendOTP(event){
    this.emailId=event.target.elements[0].value;
    console.log("Forgor password ts",this.emailId);
    this.forgotPasswordService.save(this.emailId).subscribe(
      data=>{
        console.log(data);
        console.log(data.status);
        console.log(data.message);
        
        if(data.status == 404){
          this.emailNotRegistered=true;
        }else if(data.status == 200){
          this.emailNotRegistered=false;
          this.router.navigate(['/enter-otp']);
          localStorage.setItem('otpEmail',this.emailId);
        }

      }
    );
  }


}
