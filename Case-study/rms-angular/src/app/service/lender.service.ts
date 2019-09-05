import { Injectable } from '@angular/core';
import { Lender } from '../model/lender';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import Links from './../links.module';
import { Financialanalyst } from '../model/financialanalyst';
import { Policy } from '../model/policy';
import { Creditapplication } from '../model/creditapplication';
import { AssignCreditDTO } from '../lender/view-credit-app/view-credit-app.component';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { ServerResponse } from '../model/server-response';

@Injectable({
  providedIn: 'root'
})
export class LenderService {
  currentUserId : string;

  private serverResponse = new BehaviorSubject<ServerResponse>(null);
  currentUser = this.serverResponse.asObservable();
  constructor(private http: HttpClient) {
   
   }

  public save(lender: Lender) {
    return this.http.post<Lender>(Links.REGISTER_LENDER_URL, lender).pipe(
      map( 
        serverdataResponse => {
          console.log(" --Registeration--")
          console.log(serverdataResponse)
          this.updateServerResopnse(serverdataResponse);
       //  sessionStorage.setItem('username',username);
       //  sessionStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
       //  localStorage.setItem('username',username);
       //  localStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
        // localStorage.setItem('currentUser', JSON.stringify(userData));
         return serverdataResponse;
        },(response) => response.json()
       )
     );
  }

  updateServerResopnse(serverResponse){
    this.serverResponse.next(serverResponse);
    console.log('Status',serverResponse.status);
    console.log('message =',serverResponse.message);
    console.log('Value of current server resopnse updated', serverResponse);   
  }
  
  getLenders() {
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<Lender[]>(Links.ACITVE_LENDER_URL, { headers });
    }
  }

  public addFA(financialanalyst:Financialanalyst){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      console.log('Inside View Recied Compnent Ts', currentUser);
      this.currentUserId = currentUser.userId;

      return this.http.post<Financialanalyst>(Links.LENDER_ADD_FA_URL+this.currentUserId,financialanalyst,{headers}).pipe(
        map( 
          data => {
            console.log(" --Financial Analyst Added--")
            console.log(data);
            // this.updateServerResopnse(serverdataResponse);
           return data;
          },(response) => response.json()
         )
       );
    }
  }

  public viewPolicy(){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      console.log('Inside View Recied Compnent Ts', currentUser);
      this.currentUserId = currentUser.userId;
     return this.http.get<Policy[]>(Links.LENDER_VIEW_POLICY_URL+this.currentUserId,{headers});
  }
}

  public addPolicy(policy:Policy){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      console.log('Inside View Recied Compnent Ts', currentUser);
      this.currentUserId = currentUser.userId;
    return this.http.post<Policy>(Links.LENDER_ADD_POLICY_URL+this.currentUserId,policy,{headers}).pipe(
      map( 
        data => {
          console.log(" --New Policy Added--")
          console.log(data);
          return data;
        },(response) => response.json()
       )
     );
  }
}

 public viewFA(){
  const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      console.log('Inside View Recied Compnent Ts', currentUser);
      this.currentUserId = currentUser.userId;
  
  return this.http.get<Financialanalyst[]>(Links.LENDER_VIEW_FA_URL+this.currentUserId,{headers});
 }
}

 public getCA(){
  const authJSON = localStorage.getItem('auth');
  console.log(authJSON);
  if (authJSON && authJSON !== '') {
    const headers = new HttpHeaders(JSON.parse(authJSON));
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View Recied Compnent Ts', currentUser);
    this.currentUserId = currentUser.userId;

   return this.http.get<Creditapplication[]>(Links.LENDER_VIEW_CREDIT_APPLICATION_URL+this.currentUserId, {headers});
  }
 }


 public updatePolicy(policy:Policy[]){
  const authJSON = localStorage.getItem('auth');
  console.log(authJSON);
  if (authJSON && authJSON !== '') {
    const headers = new HttpHeaders(JSON.parse(authJSON));
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View Recied Compnent Ts', currentUser);
    this.currentUserId = currentUser.userId;

  return this.http.post<Policy[]>(Links.LENDER_UPDATE_POLICY_URL+this.currentUserId,policy,{headers});
  }
 }

 
 public assignCreditApplication(assignCreditDTO:AssignCreditDTO){
  const authJSON = localStorage.getItem('auth');
  console.log(authJSON);
  if (authJSON && authJSON !== '') {
    const headers = new HttpHeaders(JSON.parse(authJSON));
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View Recied Compnent Ts', currentUser);
    this.currentUserId = currentUser.userId;
    return this.http.post<AssignCreditDTO>(Links.LENDER_ASSIGN_CREDIT_APP+this.currentUserId,assignCreditDTO,{headers}).pipe(
      map( 
        data => {
          console.log(" --Credit Application Assigned Financial Analyst--")
          console.log(data);
          // this.updateServerResopnse(serverdataResponse);
         return data;
        },(response) => response.json()
       )
     );
  }
 }


 public viewMoreDetails(appId){
  const authJSON = localStorage.getItem('auth');
  console.log(authJSON);
  if (authJSON && authJSON !== '') {
    const headers = new HttpHeaders(JSON.parse(authJSON));
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View More Details', currentUser);
    this.currentUserId = currentUser.userId;
    return this.http.get<any>(Links.LENDER_VIEW_MORE_DETAILS_URL+this.currentUserId+'&appId='+appId,{headers}).pipe(
      map( 
        data => {
          console.log(" --Credit Application View More Details--")
          console.log(data);
          // this.updateServerResopnse(serverdataResponse);
         return data;
        },(response) => response.json()
       )
     );
  }

 }



}