import { DataService } from 'src/app/service/data.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';
import { AdminService } from 'src/app/service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-active-lenders',
  templateUrl: './active-lenders.component.html',
  styleUrls: ['./active-lenders.component.css']
})
export class ActiveLendersComponent implements OnInit {
  activeLenderColumns = ['lenderId','lenderName','lenderEmail','loanInterest','numberOfApplicationRequests','tenureRange','lenderAmountRange','numberOfFinancialAnalyst','update'];
  activeLenderDataSource: MatTableDataSource<any>;
  Lenders:any;
  currentUserName:string;
  currentUserEmail:string;
  horizontalPosition:MatSnackBarHorizontalPosition = 'right';
  verticalPosition : MatSnackBarVerticalPosition = 'top';
  message : string;
  rowGreen = false;
  lastlyActivated : string;
  constructor(private adminService:AdminService,private router: Router ,private snackBar: MatSnackBar,private data : DataService) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if(!(currentUser.userRole === "ROLE_ADMIN")){
      this.router.navigate(['access-denied']);
      }
   
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail; 
  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {

    this.data.currentMessage.subscribe(message => this.message = message)
    

    if(this.message === 'initial'){
      this.rowGreen=false; 
      
    }else if(this.message === 'lenderactivated'){
      this.rowGreen=true; 
     
    } else{
      this.rowGreen=false; 
      
    }
    

    this.adminService.getActiveLenders().subscribe(
      response => {
        
        if (response !== [] && response) {
          this.Lenders = response;
          this.activeLenderDataSource = new MatTableDataSource(this.Lenders);
         
          this.lastlyActivated=this.activeLenderDataSource.data[0].lenderId;
          this.activeLenderDataSource.sort = this.sort;
          this.activeLenderDataSource.paginator = this.paginator;
        }
      }, error => {
        this.Lenders = []
      }
    );
 
  }



  
  applyFilter(filterValue: string) {
    this.activeLenderDataSource.filter = filterValue.trim().toLowerCase();
  }

  activeLender(lenderId){
    
    this.adminService.deactivateLender(lenderId).subscribe(
    response => {
      console.log("Inside View Recieved", response);
      if (response !== [] && response) {
        console.log(response.message);
        this.ngOnInit();
        this.data.setMessage('lenderdeactivated');
        this.openSnackBar('Lender ID = '+lenderId+' deactivated ','Successfuly');
        this.router.navigate(['view-inactive-lenders']);


      }
    }, error => {
      console.log(error);
    }
    );
  
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