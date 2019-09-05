import { DataService } from 'src/app/service/data.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { ActiveLendersComponent } from '../active-lenders/active-lenders.component';


@Component({
  selector: 'app-inactive-lenders',
  templateUrl: './inactive-lenders.component.html',
  styleUrls: ['./inactive-lenders.component.css']
})
export class InactiveLendersComponent implements OnInit {
  inactiveLenderColumns =  ['lenderId','lenderName','lenderEmail','loanInterest','numberOfApplicationRequests','tenureRange','lenderAmountRange','numberOfFinancialAnalyst','update'];
  inactiveLenderDataSource: MatTableDataSource<any>;
  lenders:any;
  currentUserName:string;
  currentUserEmail:string;
  horizontalPosition:MatSnackBarHorizontalPosition = 'right';
  verticalPosition : MatSnackBarVerticalPosition = 'top';
  message : string;
  rowRed = false;
  lastlyDeactivated : string;
  constructor(private adminService:AdminService,private router: Router,private snackBar: MatSnackBar,private data: DataService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
   
    if (!(currentUser.userRole === "ROLE_ADMIN")) {
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
      this.rowRed=false; 
      
    }else if(this.message === 'lenderdeactivated'){
      this.rowRed=true; 
     
    } else{
      this.rowRed=false; 
      
    }

    this.adminService.getInactiveLenders().subscribe(
      response => {
        console.log("Inside Inactive Lenders", response);
        if (response !== [] && response) {
          this.lenders = response;
          this.inactiveLenderDataSource = new MatTableDataSource(this.lenders);
          this.lastlyDeactivated=this.inactiveLenderDataSource.data[0].lenderId;
          this.inactiveLenderDataSource.sort = this.sort;
          this.inactiveLenderDataSource.paginator = this.paginator;
        }
      }, error => {
        this.lenders = []
      }
    );
 
  }

  
  applyFilter(filterValue: string) {
    this.inactiveLenderDataSource.filter = filterValue.trim().toLowerCase();
  }

  inactiveLender(lenderId){
  

  this.adminService.activateLender(lenderId).subscribe(
    response => {
      
      if (response !== [] && response) {
        
        this.ngOnInit();
        this.data.setMessage('lenderactivated');
        this.openSnackBar('Lender ID = '+lenderId+' activated ','Successfuly');
  
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