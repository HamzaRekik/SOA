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

  getPaidFacturesWithPaymentMethod(paymentMethod: String) {
    const paidFacturesUrl = this.baseUrl + `/reglements_by_methode_payment/${paymentMethod}`;
    return this.http.get<Array<Reglement>>(paidFacturesUrl)
  }

  getPaidFacturesWithRangeAmount(max: number) {
    const paidFacturesUrl = this.baseUrl + `/montantReglement/0/${max}`;
    return this.http.get<Array<Reglement>>(paidFacturesUrl)
  }

  getPaidFacturesWithRangeDate(start: string, end: string) {
    const paidFacturesUrl = this.baseUrl + `/dateReglement/${start}/${end}`;
    return this.http.get<Array<Reglement>>(paidFacturesUrl)
  }

}
