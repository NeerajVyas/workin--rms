import { ForgotPasswordService } from './service/forgot-password.service';
import { ChangePasswordComponent } from './login-form/change-password/change-password.component';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MaterialModule} from './material/material.module';
import {MatButtonModule} from '@angular/material/button';

//  angular material module

import { RegisterComponent } from './register/register.component';
import { LenderHomeComponent} from './lender/lender-home/lender-home.component';
import { Routes,RouterModule } from '@angular/router';
import { AddPolicyComponent } from './lender/add-policy/add-policy.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LogoutComponent } from './logout/logout.component';
import { ForgotPasswordComponent } from './login-form/forgot-password/forgot-password.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';


import { AuthenticationService } from './login-form/authentication.service';
import { AuthguardService } from './login-form/authguard.service';


import { AppHomeComponent } from './app-home/app-home.component';
import { LenderFormComponent } from './register/lender-form/lender-form.component';
import { UserFormComponent } from './register/user-form/user-form.component';
import { LenderService } from './service/lender.service';
import { BorrowerService } from './service/borrower.service';
import { HttpClientModule } from '@angular/common/http';
import { BorrowerHomeComponent } from './borrower/borrower-home/borrower-home.component';
import { FinancialAnalystHomeComponent } from './financial-analyst/financial-analyst-home/financial-analyst-home.component';
import { FinancialAnalystSidebarComponent } from './financial-analyst/financial-analyst-sidebar/financial-analyst-sidebar.component';
import { ViewDetailsDialougComponent } from './financial-analyst/view-received-credit-app/view-details-dialoug/view-details-dialoug.component';
import {ViewReceivedCreditAppComponent} from './financial-analyst/view-received-credit-app/view-received-credit-app.component';
import { ViewDetailsDialougModule } from './financial-analyst/view-received-credit-app/view-details-dialoug/view-details-dialoug.module';
import { BorrowerHomeTableComponent } from './borrower/borrower-home-table/borrower-home-table.component';
import { CheckCreditScoreComponent } from './borrower/check-credit-score/check-credit-score.component';
import { CreditApplicationComponent } from './borrower/credit-application/credit-application.component';
import { PolicyService } from './service/policy.service';
import { CreditAppService } from './service/credit-app.service';
import { BorrowerHeaderComponent } from './borrower/borrower-header/borrower-header.component';
import { LenderHeaderComponent } from './lender/lender-header/lender-header.component';
import { AddFinancialAnalystComponent } from './lender/add-financial-analyst/add-financial-analyst.component';
import { ViewPolicyComponent } from './lender/view-policy/view-policy.component';
import { ViewFinancialAnalystComponent } from './lender/view-financial-analyst/view-financial-analyst.component';
import { NewPolicyComponent } from './lender/add-policy/new-policy/new-policy.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AppHeaderComponent } from './app-header/app-header.component';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { PreviousCreditScoreComponent } from './borrower/previous-credit-score/previous-credit-score.component';
import { TopBorrowersComponent } from './financial-analyst/top-borrowers/top-borrowers.component';
import { ViewCreditAppComponent, ModalComponent  } from './lender/view-credit-app/view-credit-app.component';
import { ViewPreviousUpdatedCreditAppComponent } from './financial-analyst/view-previous-updated-credit-app/view-previous-updated-credit-app.component';
import { ViewLendersComponent } from './admin/view-lenders/view-lenders.component';
import { ViewBorrowersComponent } from './admin/view-borrowers/view-borrowers.component';
import { AdminViewFinancialAnalystComponent } from './admin/admin-view-financial-analyst/admin-view-financial-analyst.component';
import { InactiveLendersComponent } from './admin/inactive-lenders/inactive-lenders.component';
import { ActiveLendersComponent } from './admin/active-lenders/active-lenders.component';
import { ActiveBorrowersComponent } from './admin/active-borrowers/active-borrowers.component';
import { InactiveBorrowersComponent } from './admin/inactive-borrowers/inactive-borrowers.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EnterOtpComponent } from './login-form/enter-otp/enter-otp.component';
import { DataService } from './service/data.service';
import { ViewDetailsLenderDialogComponent } from './lender/view-credit-app/view-details-lender-dialog/view-details-lender-dialog.component';
import { ViewDetailsBorrowerDialogComponentComponent } from './borrower/previous-credit-score/view-details-borrower-dialog-component/view-details-borrower-dialog-component.component';



const appRoutes:Routes = [
  {
    path : '',
    component : AppHomeComponent
    },
  {
  path : 'login',
  component : LoginFormComponent
  },
  {
  path : 'forgotpassword',
  component : ForgotPasswordComponent
  },
  {
    path : 'admin-home',
    component : AdminHomeComponent,
    canActivate:[AuthguardService]
  },
 {
    path : 'logout',
    component : LogoutComponent,
   canActivate:[AuthguardService]
  },
  {
    path:'lender-home',
    component : LenderHomeComponent,
    canActivate:[AuthguardService]
    },
    {
      path:'add-policy',
      component:AddPolicyComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-policy',
      component:ViewPolicyComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'add-financial-analyst',
      component:AddFinancialAnalystComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'lender-header',
      component:LenderHeaderComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'register-selection',
      component:RegisterComponent
    }, 
    {
      path:'lender-registration',
      component:LenderFormComponent 
    },
    {
      path:'new-policy',
      component: NewPolicyComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-fa',
      component: ViewFinancialAnalystComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-credit-app',
      component: ViewCreditAppComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'borrower-registration',
      component:UserFormComponent
    },
    {
      path:'borrower-home',
      component:BorrowerHomeComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'lenders-list',
      component:BorrowerHomeTableComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'credit-application',
      component:CreditApplicationComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'check-credit-score',
      component:CheckCreditScoreComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'financial-analyst-home',
      component:FinancialAnalystHomeComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'previous-credit-score',
      component:PreviousCreditScoreComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'about-us',
      component:AboutUsComponent,
      
    },
    {
      path:'contact-us',
      component:ContactUsComponent,
      
    },
    {
      path:'view-active-lenders',
      component:ActiveLendersComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-inactive-lenders',
      component:InactiveLendersComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-active-borrowers',
      component:ActiveBorrowersComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'view-inactive-borrowers',
      component:InactiveBorrowersComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'access-denied',
      component:PageNotFoundComponent,
      canActivate:[AuthguardService]
    },
    {
      path:'enter-otp',
      component:EnterOtpComponent,
    },
    {
      path:'forgotpassword',
      component:ForgotPasswordComponent,
    },
    {
      path:'change-password',
      component:ChangePasswordComponent,
    },
    {
      path:'fa-view-received-credit-app',
      component:ViewReceivedCreditAppComponent,
    },
    {
      path:'fa-top-ten-borrowers',
      component: TopBorrowersComponent,
    },
    {
      path:'fa-previous-credit-app',
      component: ViewPreviousUpdatedCreditAppComponent,
    },
   
]




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LenderHomeComponent,
    AddPolicyComponent,
    LoginFormComponent,
    LogoutComponent,
    ForgotPasswordComponent,
    AdminHomeComponent,
    AppHomeComponent,
    LenderFormComponent,
    UserFormComponent,
    BorrowerHomeComponent,
    FinancialAnalystHomeComponent,
    FinancialAnalystSidebarComponent,
    ViewReceivedCreditAppComponent,
    BorrowerHomeTableComponent,
    CheckCreditScoreComponent,
    CreditApplicationComponent,
    BorrowerHeaderComponent,
    LenderHeaderComponent,
    AddFinancialAnalystComponent,
    ViewPolicyComponent,
    ViewFinancialAnalystComponent,
    NewPolicyComponent,
    ModalComponent,
    AppHeaderComponent,
    AppFooterComponent,
    PreviousCreditScoreComponent,
    TopBorrowersComponent,
    ViewCreditAppComponent,
    ViewPreviousUpdatedCreditAppComponent,
    ViewLendersComponent,
    ViewBorrowersComponent,
    AdminViewFinancialAnalystComponent,
    InactiveLendersComponent,
    ActiveLendersComponent,
    ActiveBorrowersComponent,
    InactiveBorrowersComponent,
    ContactUsComponent,
    AboutUsComponent,
    PageNotFoundComponent,
    ForgotPasswordComponent,
    EnterOtpComponent,
    ChangePasswordComponent,
    ViewDetailsLenderDialogComponent,
    ViewDetailsBorrowerDialogComponentComponent
    
  ],
  imports: [
    BrowserModule, 
    HttpModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatButtonModule,
    ViewDetailsDialougModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    ModalModule.forRoot()
  ], 
  providers: [AuthenticationService,AuthguardService, LenderService, BorrowerService ,PolicyService, CreditAppService,ForgotPasswordService,DataService],
  bootstrap: [AppComponent],
  entryComponents : [ModalComponent,ViewDetailsLenderDialogComponent,ViewDetailsBorrowerDialogComponentComponent]
})
export class AppModule { }
