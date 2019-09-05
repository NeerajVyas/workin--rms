import { Component} from '@angular/core';
import { Borrower } from 'src/app/model/borrower';
import { ActivatedRoute, Router } from '@angular/router';
import { BorrowerService } from 'src/app/service/borrower.service';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  horizontalPosition:MatSnackBarHorizontalPosition = 'center';
  verticalPosition : MatSnackBarVerticalPosition = 'top';

  borrower:Borrower;

  constructor(private route: ActivatedRoute, private router:Router,private borrowerService:BorrowerService,private snackBar: MatSnackBar) {
    this.borrower= new Borrower();
  }

  openSnackBar( message,status){
    this.snackBar.open(message, status, {
      duration: 2000,
      horizontalPosition : this.horizontalPosition,
      verticalPosition : this.verticalPosition,
      panelClass: ['blue-snackbar']
    });
    
  }

  onSubmit(){
  
    this.borrowerService.save(this.borrower).subscribe( data => {
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
        this.router.navigate(['borrower-registration'])
       });
  }

}


