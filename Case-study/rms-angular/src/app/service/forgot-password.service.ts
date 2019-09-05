import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Links from '../links.module';
import { post } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {

  constructor(private httpClient: HttpClient) { }



  save(emailId) {
    console.log("FP In service", emailId);

    return this.httpClient.get<any>(Links.FORGOT_PASS_EMAIL+emailId).pipe(
      map(response=>{
        return response;}));
  }

  verifyOTP(postJson) {
    console.log(postJson);
    return this.httpClient.post<any>(Links.FORGOT_PASS_OTP_VERIFY,postJson).pipe(
      map(response=>{
        return response;}));
  }


  setNewPassword(postJson){
    return this.httpClient.put<any>(Links.FORGOT_PASS_SET_NEW_PASS,postJson).pipe(
      map(response=>{
        return response;}));
  }


}
