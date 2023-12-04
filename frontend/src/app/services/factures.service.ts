import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Facture} from "./models/facture";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FacturesService {
  private baseUrl = "http://localhost:8080";

  constructor(private http:HttpClient) {}

  getAllFactures(){
    const facturesUrl = this.baseUrl+"/factures";
    return this.http.get<Array<Facture>>(facturesUrl)
  }
  getPaidFactures(){
    const paidFacturesUrl = this.baseUrl+"/paye";
    return this.http.get<Array<Facture>>(paidFacturesUrl)
  }
  getUnpaidFactures(){
    const paidFacturesUrl = this.baseUrl+"/non_paye";
    return this.http.get<Array<Facture>>(paidFacturesUrl)
  }

  makeReglement(factures: Array<Facture>): Observable<Array<Facture>> {
    const endpoint = this.baseUrl + "/set_factures";
    return this.http.post<Array<Facture>>(endpoint, factures);
  }
}
