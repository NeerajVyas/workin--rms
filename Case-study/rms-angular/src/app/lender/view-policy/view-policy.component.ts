import { Component, OnInit, ViewChild } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-policy',
  templateUrl: './view-policy.component.html',
  styleUrls: ['./view-policy.component.css']
})
export class ViewPolicyComponent implements OnInit {

  policy:any;
  currentUserName: string;
  currentUserEmail: string;
  policyColumns = ['policyName','threshold', 'policyWeightage','policyStatus'];
  PolicyDataSource: MatTableDataSource<any>;
  constructor(private lenderService:LenderService,private router : Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail
   }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {

    this.lenderService.viewPolicy().subscribe(
      response => {
        console.log("Inside policies ", response);
        if (response !== [] && response) {
          this.policy = response;
          this.PolicyDataSource = new MatTableDataSource(this.policy);
          console.log("Inside View policy", JSON.stringify(this.PolicyDataSource.data));
          this.PolicyDataSource.sort = this.sort;
          this.PolicyDataSource.paginator = this.paginator;
        }

      }, error => {
        this.policy = []
      });

  //  this.lenderService.viewPolicy().subscribe(response=>this.handleSuccessfulResponse(response));
  }



  applyFilter(filterValue: string) {
    this.PolicyDataSource.filter = filterValue.trim().toLowerCase();
  }



}

/* import { Component, OnInit } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';

@Component({
  selector: 'app-view-policy',
  templateUrl: './view-policy.component.html',
  styleUrls: ['./view-policy.component.css']
})
export class ViewPolicyComponent implements OnInit {

  policy:string[];
  constructor(private lenderService:LenderService) { }

  ngOnInit() {
    this.lenderService.viewPolicy().subscribe(response=>this.handleSuccessfulResponse(response));
  }
handleSuccessfulResponse(response){
  this.policy=response;
}
}
 */