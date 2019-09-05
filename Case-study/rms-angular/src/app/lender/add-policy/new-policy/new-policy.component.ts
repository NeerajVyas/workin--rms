import { Component, OnInit } from '@angular/core';
import { Policy } from 'src/app/model/policy';
import { ActivatedRoute, Router } from '@angular/router';
import { LenderService } from 'src/app/service/lender.service';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-new-policy',
  templateUrl: './new-policy.component.html',
  styleUrls: ['./new-policy.component.css']
})
export class NewPolicyComponent  {
  currentUserName: string;
  currentUserEmail: string;
  policy:Policy;
  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute, private router:Router,private lenderService:LenderService,private snackBar: MatSnackBar) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
    this.policy= new Policy();
  }

  onSubmit(){
   
    this.lenderService.addPolicy(this.policy).subscribe(  data => {
      console.log('--Policy Added  --');
      console.log(data)
        this.openSnackBar('Policy Added','Successfully');
        this.router.navigate(['view-policy'])
       // this.router.navigate(['lender-home'], {queryParams:{addPolicy: true}});
    },
    error => {
      //if(error.status === 401) this.openSnackBar('Login','Failed');
       console.log('ERROR')
       console.log('error status ',error.status)
       if(error.status ==409) this.openSnackBar('Policy Already','Exists');
      console.log('error message', error.message)
     // this.router.navigate(['new-policy'])
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
