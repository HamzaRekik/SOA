import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Facture} from "./models/facture";
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FacturesService {
    private baseUrl = "http://localhost:8080";
    private  userId = localStorage.getItem('user_id')
    constructor(private http: HttpClient) {
    }

    getAllFactures() {
        const facturesUrl = this.baseUrl + "/factures";
        return this.http.get<Array<Facture>>(facturesUrl)
    }

    getPaidFactures() {
        const paidFacturesUrl = this.baseUrl + "/paye";
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getPaidFacturesWithPaymentMethod(paymentMethod: String) {
        const paidFacturesUrl = this.baseUrl + `/factures_paye_by_methode_payment/${this.userId}/${paymentMethod}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getPaidFacturesWithRangeAmount(max: number) {
        const paidFacturesUrl = this.baseUrl + `/montant/${this.userId}/0/${max}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getPaidFacturesWithRangeDate(start: string, end: string) {
        const paidFacturesUrl = this.baseUrl + `/date/${this.userId}/${start}/${end}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getUnpaidFactures() {
        const paidFacturesUrl = this.baseUrl + `/non_paye/${this.userId}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getNumberOfUnpaidFactures() {
        const paidFacturesUrl = this.baseUrl + `/count_facture_non_paye/${this.userId}`;
        return this.http.get<number>(paidFacturesUrl)
    }

    makeReglement(factures: Array<Facture>): Observable<Array<Facture>> {
        const endpoint = this.baseUrl +`/${this.userId}/payer_factures`;
        return this.http.post<Array<Facture>>(endpoint, factures);
    }
}
