import { Component, OnInit, ViewChild } from '@angular/core';
import { TopBorrowers } from 'src/app/model/top-borrowers';
import { FinancialAnalystService } from 'src/app/service/financial-analyst.service';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top-borrowers',
  templateUrl: './top-borrowers.component.html',
  styleUrls: ['./top-borrowers.component.css']
})
export class TopBorrowersComponent implements OnInit {
  topborrowers: TopBorrowers[];
  topBorrowersColumns = ['userName', 'userEmail', 'companyName', 'creditScore'];

  topBorrowersDataSource: MatTableDataSource<TopBorrowers>;

  currentUserEmail: string;
  currentUserName: string;
  constructor(private financialanalyst: FinancialAnalystService, private router: Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {
    this.financialanalyst.topBorrowers().subscribe(
      response => {
        
        if (response !== [] && response) {
          this.topborrowers = response;
          
          this.topBorrowersDataSource = new MatTableDataSource(this.topborrowers);
          
          this.topBorrowersDataSource.sort = this.sort;
          this.topBorrowersDataSource.paginator = this.paginator;
        }
      }, error => {
        this.topborrowers = []
      }
    );
  }

  applyFilter(filterValue: string) {
    this.topBorrowersDataSource.filter = filterValue.trim().toLowerCase();
  }


}
