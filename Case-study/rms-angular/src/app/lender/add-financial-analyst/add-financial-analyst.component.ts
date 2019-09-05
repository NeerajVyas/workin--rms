import { Component, OnInit } from '@angular/core';
import { Financialanalyst } from 'src/app/model/financialanalyst';
import { ActivatedRoute, Router } from '@angular/router';
import { LenderService } from 'src/app/service/lender.service';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-add-financial-analyst',
  templateUrl: './add-financial-analyst.component.html',
  styleUrls: ['./add-financial-analyst.component.css']
})
export class AddFinancialAnalystComponent {

  financialanalyst:Financialanalyst;
  currentUserName: string;
  currentUserEmail: string;

  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'bottom';

  constructor(private route: ActivatedRoute, private router:Router,private lenderService:LenderService,private snackBar: MatSnackBar) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
    this.financialanalyst= new Financialanalyst();
  }

  onSubmit(){
    this.lenderService.addFA(this.financialanalyst).subscribe( data => {
      console.log('--FA Added  --');
      console.log(data)
        this.openSnackBar('Financial Analyst Added','Successfully');
       this.router.navigate(['view-fa'])
//       this.router.navigate(['add-financial-analyst'])
        
    },
    error => {
      //if(error.status === 401) this.openSnackBar('Login','Failed');
       console.log('ERROR')
       console.log('error status ',error.status)
       if(error.status ==409) this.openSnackBar(' FA Already','Exist');
      console.log('error message', error.message)
     this.router.navigate(['add-financial-analyst'])
     });
  }

  openSnackBar( message,status){
    this.snackBar.open(message, status, {
      duration: 2500,
      horizontalPosition : this.horizontalPosition,
      verticalPosition : this.verticalPosition,
      panelClass: ['blue-snackbar']
    });
    
  }
}
