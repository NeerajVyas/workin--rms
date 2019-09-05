import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';
import { AdminService } from 'src/app/service/admin.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-active-borrowers',
  templateUrl: './active-borrowers.component.html',
  styleUrls: ['./active-borrowers.component.css']
})
export class ActiveBorrowersComponent implements OnInit {
  activeBorrowerColumns = ['borrowerId', 'borrowerName', 'borrowerEmail', 'noOfApplication', 'update'];
  activeBorrowerDataSource: MatTableDataSource<any>;
  currentUserName: string;
  currentUserEmail: string;
  activeBorrowers: any;
  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  lastlyActivated : string;
  message : string;
  rowGreen = false;

  constructor(private adminService: AdminService, private router: Router, private snackBar: MatSnackBar,private data : DataService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_ADMIN")) {
      this.router.navigate(['access-denied']);
    }
    console.log('Inside ADMIN ACTIVE BORROWERS - ', currentUser);
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {
  //  var currentUser = JSON.parse(localStorage.getItem('currentUser'));

    this.data.currentMessage.subscribe(message => this.message = message)
   

    if(this.message === 'initial'){
      this.rowGreen=false; 
      console.log(this.rowGreen)
    }else {
      this.rowGreen=true; 
      console.log(this.rowGreen)
    }
    

     this.adminService.getActiveBorrowers().subscribe(
        response => {
          console.log("Inside View active Borrowers", response);
          if (response !== [] && response) {
            this.activeBorrowers = response;
            this.activeBorrowerDataSource = new MatTableDataSource(this.activeBorrowers);
            
            this.lastlyActivated=this.activeBorrowerDataSource.data[0].borrowerId;
                    
            this.activeBorrowerDataSource.sort = this.sort;
            this.activeBorrowerDataSource.paginator = this.paginator;
          }
        }, error => {
          this.activeBorrowers = []
        }
      );
    
  }
  

  applyFilter(filterValue: string) {
    this.activeBorrowerDataSource.filter = filterValue.trim().toLowerCase();
  }

  deactiveBorrower(borrowerId) {
    console.log('Active Borrower Id = ', borrowerId);

    this.adminService.deactivateBorrower(borrowerId).subscribe(
      response => {
       
        if (response !== [] && response) {
          console.log(response.message);
          
          this.openSnackBar('Borrower ID = ' + borrowerId + ' deactivated ', 'Successfuly');
          this.data.setMessage('deactivated');
          this.router.navigate(['view-inactive-borrowers']);
          

        }
      }, error => {
        console.log(error);
      }
    );

  }


  openSnackBar(message, status) {
    this.snackBar.open(message, status, {
      duration: 2500,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      panelClass: ['blue-snackbar']
    });

  }

}