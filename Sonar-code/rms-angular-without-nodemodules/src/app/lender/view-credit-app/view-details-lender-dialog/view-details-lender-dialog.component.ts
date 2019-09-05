import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-view-details-lender-dialog',
  templateUrl: './view-details-lender-dialog.component.html',
  styleUrls: ['./view-details-lender-dialog.component.css']
})
export class ViewDetailsLenderDialogComponent implements OnInit {
  dataResponse:any;
  constructor(private dialogRef: MatDialogRef<ViewDetailsLenderDialogComponent>,@Inject(MAT_DIALOG_DATA) public data: any) {
    this.dataResponse=this.data.viewDetails;
   }
  
  ngOnInit() {
    console.log('inside dialog',this.data.viewDetails);
  }

  close(){
    this.dialogRef.close('closed');
   }

}
