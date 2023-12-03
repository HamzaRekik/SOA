import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Facture} from "./models/facture";

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
}
