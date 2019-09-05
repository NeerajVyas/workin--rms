import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBarVerticalPosition, MatSnackBarHorizontalPosition, MatSnackBar } from '@angular/material';
import { AdminService } from 'src/app/service/admin.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-inactive-borrowers',
  templateUrl: './inactive-borrowers.component.html',
  styleUrls: ['./inactive-borrowers.component.css']
})
export class InactiveBorrowersComponent implements OnInit {

  inactiveBorrowerColumns = ['borrowerId','borrowerName','borrowerEmail','noOfApplication','update'];


  inactiveBorrowerDataSource: MatTableDataSource<any>;
  currentUserName:string;
  currentUserEmail:string;
  inactiveBorrowers:any;
  horizontalPosition:MatSnackBarHorizontalPosition = 'right';
  verticalPosition : MatSnackBarVerticalPosition = 'top';
  message : string;
  rowRed = false;
  lastlyDeactivated : string;
  constructor(private adminService:AdminService ,private router: Router,private snackBar: MatSnackBar,private data: DataService) { 
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
     
    }else if(this.message === 'deactivated'){
      this.rowRed=true; 
    } else{
      this.rowRed=false; 
      
    }

    this.adminService.getInactiveBorrowers().subscribe(
      response => {
       
        if (response !== [] && response) {
          this.inactiveBorrowers = response;
          this.inactiveBorrowerDataSource = new MatTableDataSource(this.inactiveBorrowers);
          this.lastlyDeactivated=this.inactiveBorrowerDataSource.data[0].borrowerId;
          this.inactiveBorrowerDataSource.sort = this.sort;
          this.inactiveBorrowerDataSource.paginator = this.paginator;
        }
      }, error => {
        this.inactiveBorrowers = []
      }
    );
  }

  applyFilter(filterValue: string) {
    this.inactiveBorrowerDataSource.filter = filterValue.trim().toLowerCase();
  }

  activeBorrower(borrowerId){
    
    

    this.adminService.activateBorrower(borrowerId).subscribe(
    response => {
 
      if (response !== [] && response) {
        console.log(response.message);
        this.ngOnInit();
        this.openSnackBar('Borrower ID = '+borrowerId+' activated ','Successfuly');
        this.data.setMessage('activated');
        this.router.navigate(['view-active-borrowers']);
  
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