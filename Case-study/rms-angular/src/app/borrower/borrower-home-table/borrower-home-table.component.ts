import { Component, OnInit, ViewChild } from '@angular/core';
import { LenderService } from './../../service/lender.service';
import {  Router } from '@angular/router';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-borrower-home-table',
  templateUrl: './borrower-home-table.component.html',
  styleUrls: ['./borrower-home-table.component.css']
})
export class BorrowerHomeTableComponent implements OnInit {
 
 activeLenderTableColumn =  ['userName', 'loanInterest', 'tenureRange', 'loanAmountRange', 'apply',];
 activeLenderTableDataSource: MatTableDataSource<any>;
 lendersData: any;


 currentUserName:string;
 currentUserEmail:string;
  constructor(private lenderService: LenderService, private router: Router) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_BORROWER")) {
      this.router.navigate(['access-denied']);
    }  this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail; 
  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  ngOnInit() {
    this.lenderService.getLenders().subscribe(
      response => {
        console.log("Inside View Recieved", response);
        if (response !== [] && response) {
          this.lendersData = response;
          this.activeLenderTableDataSource = new MatTableDataSource(this.lendersData);
          this.activeLenderTableDataSource.sort = this.sort;
          this.activeLenderTableDataSource.paginator = this.paginator;
        }


      }, error => {
        this.lendersData = []
      }
    );
}

  routeWithData(lender) {
    this.router.navigate(['/credit-application'], {queryParams:{lender: lender.userId}});
  }

  applyFilter(filterValue: string) {
    this.activeLenderTableDataSource.filter = filterValue.trim().toLowerCase();
  }

}
