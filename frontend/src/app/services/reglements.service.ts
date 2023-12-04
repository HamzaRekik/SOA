import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reglement } from './models/reglement';

@Injectable({
  providedIn: 'root'
})
export class ReglementsService {
  private baseUrl = "http://localhost:8080";

  constructor(private http:HttpClient) {}

  getAllReglements(){
    const reglementsUrl = this.baseUrl+"/reglements";
    return this.http.get<Array<Reglement>>(reglementsUrl)
  }

}
