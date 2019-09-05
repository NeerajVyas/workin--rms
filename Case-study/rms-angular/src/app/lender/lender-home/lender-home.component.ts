import { Component, TemplateRef, ViewChild } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Creditapplication } from 'src/app/model/creditapplication';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar, MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-lender-home',
  templateUrl: './lender-home.component.html',
  styleUrls: ['./lender-home.component.css']
})
export class LenderHomeComponent {
 // creditapplication: any;
  //bsModalRef: BsModalRef;
  //creditAppColumns = ['applicationId', 'companyName', 'creditScore', 'applicationStatus', 'assign'];
  //creditAppDataSource: MatTableDataSource<any>;
  currentUserName: string;
  currentUserEmail: string;

  divViewApplicationRequests = true;

  divSetUpdatePolicy = false;
  divViewPolicy = false;
  divAddFinancialAnalyst = false;
  divViewFinancialAnalyst = false;
  divAddParameterinPolicy = false;

  //@ViewChild(MatSort, { static: true }) sort: MatSort;
  //@ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;



  constructor(private lenderService: LenderService, private modalService: BsModalService, private router: ActivatedRoute,private route:Router) {

    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.route.navigate(['access-denied']);
    }
    console.log('Inside Borrower Home - ', currentUser);
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }

  ngOnInit() {/* 
    this.lenderService.getCA().subscribe(
      response => {
        console.log("Inside credit apps", response);
        if (response !== [] && response) {
          this.creditapplication = response;
          this.creditAppDataSource = new MatTableDataSource(this.creditapplication);
          console.log("Inside View Recieved", JSON.stringify(this.creditAppDataSource.data));
          this.creditAppDataSource.sort = this.sort;
          this.creditAppDataSource.paginator = this.paginator;
        }


      }, error => {
        this.creditapplication = []
      }); */
    

  }

  /* applyFilter(filterValue: string) {
    this.creditAppDataSource.filter = filterValue.trim().toLowerCase();
  } */




  viewFinancialAnalyst() {
    this.divViewApplicationRequests = false;
    this.divSetUpdatePolicy = false;
    this.divViewPolicy = false;
    this.divAddFinancialAnalyst = false;
    this.divViewFinancialAnalyst = true;
    this.divAddParameterinPolicy = false;
  }

  addFinancialAnalyst() {
    this.divViewApplicationRequests = false;
    this.divSetUpdatePolicy = false;
    this.divViewPolicy = false;
    this.divAddFinancialAnalyst = true;
    this.divViewFinancialAnalyst = false;
    this.divAddParameterinPolicy = false;
  }
  viewPolicy() {
    this.divViewApplicationRequests = false;
    this.divSetUpdatePolicy = false;
    this.divViewPolicy = true;
    this.divAddFinancialAnalyst = false;
    this.divViewFinancialAnalyst = false;
    this.divAddParameterinPolicy = false;
  }
  setUpdatePolicy() {
    this.divViewApplicationRequests = false;
    this.divSetUpdatePolicy = true;
    this.divViewPolicy = false;
    this.divAddFinancialAnalyst = false;
    this.divViewFinancialAnalyst = false;
    this.divAddParameterinPolicy = false;
  }

  viewApplicationRequests() {
    this.divViewApplicationRequests = true;
    this.divSetUpdatePolicy = false;
    this.divViewPolicy = false;
    this.divAddFinancialAnalyst = false;
    this.divViewFinancialAnalyst = false;
    this.divAddParameterinPolicy = false;
  }

  addParameterinPolicy() {
    this.divViewApplicationRequests = false;
    this.divSetUpdatePolicy = false;
    this.divViewPolicy = false;
    this.divAddFinancialAnalyst = false;
    this.divViewFinancialAnalyst = false;
    this.divAddParameterinPolicy = true;
  }
/* 


  openModal(applicationId, financialAnalystId, borrowerId, applicationStatus) {

    console.log("App id", applicationId);

    console.log("FA id", financialAnalystId);
    console.log("Borrower id", borrowerId);
    // fetch data here

    const initialState = {
      list: [
        applicationId,
        financialAnalystId,
        borrowerId,
        applicationStatus
      ],
      title: 'Assign Credit Application'
    };
    this.bsModalRef = this.modalService.show(ModalComponent, { initialState });
    this.bsModalRef.content.closeBtnName = 'Close';
  } */
}
