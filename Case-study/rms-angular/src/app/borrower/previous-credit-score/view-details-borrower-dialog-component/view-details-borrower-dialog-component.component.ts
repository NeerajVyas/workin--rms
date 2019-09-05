import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ViewDetailsLenderDialogComponent } from 'src/app/lender/view-credit-app/view-details-lender-dialog/view-details-lender-dialog.component';

@Component({
  selector: 'app-view-details-borrower-dialog-component',
  templateUrl: './view-details-borrower-dialog-component.component.html',
  styleUrls: ['./view-details-borrower-dialog-component.component.css']
})
export class ViewDetailsBorrowerDialogComponentComponent implements OnInit {

  dataResponse:any;
  constructor(private dialogRef: MatDialogRef<ViewDetailsLenderDialogComponent>,@Inject(MAT_DIALOG_DATA) public data: any) {
    this.dataResponse=this.data.viewDetails;
   }
   ngOnInit() {
   // console.log('inside dialog',this.data.viewDetails);
  }

  close(){
    this.dialogRef.close('closed');
   }


}
