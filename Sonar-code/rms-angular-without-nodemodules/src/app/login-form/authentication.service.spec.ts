import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {

  constructor() { }

  authenticate(username, password) {
    if (username === "admin@rms.com" && password === "admin123") {
      sessionStorage.setItem('username', username)
      console.log("User Authenticated");
      return true;
    } else {
      return false;
    }
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(user)
    console.log("InsideUserLoggedIn")
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}