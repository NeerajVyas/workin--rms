import { Component, OnInit } from '@angular/core';
import { Lender } from 'src/app/model/lender';
import { ActivatedRoute, Router } from '@angular/router';
import { LenderService } from 'src/app/service/lender.service';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-lender-form',
  templateUrl: './lender-form.component.html',
  styleUrls: ['./lender-form.component.css']
})
export class LenderFormComponent {
  lender:Lender;
  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'top';

  loanCheck = false;
  tenureCheck = false;
  amountCheck=false;
  hintColor='#ff0000';
  customValidCheck=false;
  constructor(private route: ActivatedRoute, private router:Router,private lenderService:LenderService,private snackBar: MatSnackBar) {
    this.lender= new Lender();
    
  }

  openSnackBar( message,status){
    this.snackBar.open(message, status, {
      duration: 2000,
      horizontalPosition : this.horizontalPosition,
      verticalPosition : this.verticalPosition,
      panelClass: ['blue-snackbar']
    });
    
  }

  loanInterestCheck(data){
    console.log(data.viewModel);
    if(data.viewModel < 0){
      this.loanCheck=true;
      this.customValidCheck=true;
      console.log(this.loanCheck);
    }else if (data.viewModel > 30){
      this.loanCheck=true;
      this.customValidCheck=true;
      console.log(this.loanCheck);
    }else{
      this.loanCheck=false;
      this.customValidCheck=false; 
    }
  }

  tenureRangeCheck(data){
  //  console.log(data.viewModel);
    if(data.viewModel != undefined){
      var string=  data.viewModel;
      let array=string.split('-');

      // console.log(array[0]);
      // console.log(array[1]);

      //converting string to number
      var year1=+array[0];
      var year2=+array[1];

   //   console.log(year1);
   //   console.log(year2);
     /*  if( year1 ===  NaN){
        console.log('Only Numbers please');
      } */

      if(year1 >= year2){
        console.log('INVALID ');
        this.tenureCheck=true;
        this.customValidCheck=true;

      }else{
        console.log('VALID '); 
        this.tenureCheck=false;
        this.customValidCheck=false;  
      }
   
    }
  }
  amountRangeCheck(data){
    var string=  data.viewModel;
      let array=string.split('-');
      var amount1=+array[0];
      var amount2=+array[1];

      if(amount1 >= amount2){
        console.log('INVALID ');
        this.amountCheck=true;
        this.customValidCheck=true;

      }else{
        console.log('VALID '); 
        this.amountCheck=false;
        this.customValidCheck=false;  
      }
  }

 

  onSubmit(){
    this.lenderService.save(this.lender).subscribe( data => {
      console.log('--Borrower Register --');
      console.log(data)
        this.openSnackBar('Registered','Successfully');
        this.router.navigate(['login'])
      
    },
      error => {
        //if(error.status === 401) this.openSnackBar('Login','Failed');
         console.log('ERROR')
         console.log('error status ',error.status)
         if(error.status ==409) this.openSnackBar('Already','Exist');
        console.log('error message', error.message)
        this.router.navigate(['lender-registration'])
       });
  }
}
