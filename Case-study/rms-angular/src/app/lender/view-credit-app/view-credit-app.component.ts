import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar, MatDialog } from '@angular/material';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { LenderService } from 'src/app/service/lender.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewDetailsLenderDialogComponent } from './view-details-lender-dialog/view-details-lender-dialog.component';

@Component({
  selector: 'app-view-credit-app',
  templateUrl: './view-credit-app.component.html',
  styleUrls: ['./view-credit-app.component.css']
})
export class ViewCreditAppComponent implements OnInit {
  creditAppColumns = ['applicationId', 'companyName', 'creditScore', 'applicationStatus','creationDate','viewDetails','assign'];
  creditAppDataSource: MatTableDataSource<any>;
  creditapplication: any;
  bsModalRef: BsModalRef;
  currentUserName: string;
  currentUserEmail: string;
  viewMoreDetailsResponse : any;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(private lenderService: LenderService, private modalService: BsModalService, private router: ActivatedRoute,private route:Router,public dialog: MatDialog) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.route.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }

  ngOnInit() {
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
      });
    // this.lenderService.getCA().subscribe(response=>this.handleSuccessfulResponse(response));
    // this.divViewPolicy=this.router.queryParams['getValue']()['addPolicy'];
    // if(this.divViewFinancialAnalyst){
    //   this.viewPolicy();
    // }

  }




  applyFilter(filterValue: string) {
    this.creditAppDataSource.filter = filterValue.trim().toLowerCase();
  }


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
  }

  openDialog(element){
    console.log(element);
    console.log(element.applicationId);

    this.lenderService.viewMoreDetails(element.applicationId).subscribe(
      response => {
        console.log("--Details", response);
        if (response !== null && response) {
          this.viewMoreDetailsResponse=response;
          console.log('Response',this.viewMoreDetailsResponse);
          const dialogRef = this.dialog.open(ViewDetailsLenderDialogComponent,{data: {
            viewDetails : this.viewMoreDetailsResponse 
          }});
          
          
          dialogRef.afterClosed().subscribe(result => {
            console.log(`Dialog result: ${result}`);
            console.log(closed);
           // this.ngOnInit();
          });
      } 
      }, error => {
        //this.viewMoreDetailsResponse = {}
      }
    );

   
  }



}



@Component({
  selector: 'modal',
  template: `
      <div class="modal-header">
        <h4 class="modal-title pull-left">{{title}}</h4>
        <button type="button" class="close pull-right" aria-label="Close" (click)="bsModalRef.hide()">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <table id="mytable" class="table table-bordred table-striped">
      <thead>
                 
             
              <th>ID</th>
              <th>Name</th>
              <th>Pending Req</th>
              </thead>
              <tbody>
      <tr *ngFor="let financialanalyst of financialanalyst">

              <td > {{financialanalyst.financialAnalystId}}
                </td>
                <td>   {{financialanalyst.financialAnalystName}}</td>
                <td>   {{financialanalyst.pendingRequests}}</td>
                <td>
  <div> 
        <form (submit)="assign($event)">
        
        <input type="hidden" value="{{financialanalyst.financialAnalystId}}">
        
            <button mat-raised-button color="primary" (click)="bsModalRef.hide()" > ASSIGN </button>
            </form>
  </div> 
</td>
              </tr>
          </tbody>
</table>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" (click)="bsModalRef.hide()">{{closeBtnName}}</button>
      </div>
    `
})
export class ModalComponent {


  assignCreditDTO = new AssignCreditDTO();
  title: string;
  closeBtnName: string;
  list: any;
  financialanalyst: any;
  id: string;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  FAColumns = ['financialAnalystId', 'financialAnalystName', 'pendingRequests', 'assign'];
  FADataSource: MatTableDataSource<any>;
  constructor(public bsModalRef: BsModalRef, private lenderService: LenderService, private snackBar: MatSnackBar, private router: Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
   }

  ngOnInit() {
    this.lenderService.viewFA().subscribe(
      response => {
        console.log("Inside assign FA", response);
        if (response !== [] && response) {
          this.financialanalyst = response;
          this.FADataSource = new MatTableDataSource(this.financialanalyst);
          console.log("Inside assign FA", JSON.stringify(this.FADataSource.data));
        }
      });
  }


  assign(event) {
    this.id = event.target.elements[0].value;
    this.list[1] = this.id;
    console.log(event.target.elements[0].value);
    console.log("New Id in list", this.list[1]);
    console.log("App Id", this.list[0]);
    console.log("FA id", this.list[1]);
    console.log("Borrower Id ", this.list[2]);

    console.log("Application Status ", this.list[3]);
    var appId=this.list[0]+"";

    this.assignCreditDTO.applicationId = appId.substr(3);
    var faid=""+this.list[1];
    console.log(  faid.substr(2));
    this.assignCreditDTO.financialAnalystId = faid.substr(2);
    this.assignCreditDTO.borrowerId = this.list[2];
    this.assignCreditDTO.applicationStatus = "1";

    // 0 --> Hold
    // 1 --> assigned to FA
    // 2 -->  Rejected
    // 3 --> Approved
    //
    /*  console.log("App id = ",this.assignCreditDTO.applicationId);
     console.log("B ID = ",this.assignCreditDTO.borrowerId);
     console.log("FA ID = ",this.assignCreditDTO.financialAnalystId);  */
    this.lenderService.assignCreditApplication(this.assignCreditDTO).subscribe(data => {
      console.log('--FA assigned  --');
      console.log(data)
      this.openSnackBar('Credit APP Assigned', 'Successfully');
   /*    this.router.navigate(['view-credit-app']).then(() => {
        window.location.reload();
      });
 */
      this.router.navigate(['view-fa']);
        
    });
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


export class AssignCreditDTO {
  applicationId: string;
  financialAnalystId: string;
  borrowerId: string;
  applicationStatus: string;
}