import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
baseUrl = "http://localhost:8080"
  constructor(private http : HttpClient) {}

  login(email : String , password : String){
      return this.http.post(this.baseUrl+'/login' , {email : email , password : password})
  }
}
