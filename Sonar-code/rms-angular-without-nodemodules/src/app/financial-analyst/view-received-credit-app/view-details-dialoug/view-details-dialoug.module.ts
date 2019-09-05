import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import {ViewDetailsDialougComponent} from './view-details-dialoug.component';

import {MatDialogModule} from '@angular/material/dialog';
import { MatButtonModule, MatInputModule, MatFormFieldModule } from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import { FormsModule } from '@angular/forms';



@NgModule( {

    declarations : [ViewDetailsDialougComponent],
    imports : [MatDialogModule, MatButtonModule,MatSelectModule,MatInputModule,MatFormFieldModule,MatCardModule,FormsModule, CommonModule],
    entryComponents : [ViewDetailsDialougComponent],
    exports : [ViewDetailsDialougComponent,MatButtonModule,MatSelectModule,MatInputModule,MatFormFieldModule,MatCardModule,FormsModule]
}
)
export class ViewDetailsDialougModule{}