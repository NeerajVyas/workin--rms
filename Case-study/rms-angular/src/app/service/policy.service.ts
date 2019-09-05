import { Lender } from 'src/app/model/lender';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Policy } from 'src/app/model/policy';
import Links from './../links.module'
@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  lender:Lender;
    
  constructor(private httpClient: HttpClient) {
    this.lender = new Lender();
   }

  getPolicies(userId){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
    this.lender.userId=userId;
    return this.httpClient.post<Policy[]>(Links.LENDERHOME_GET_POLICIES_URL,this.lender, {headers});
    }
  }
}
