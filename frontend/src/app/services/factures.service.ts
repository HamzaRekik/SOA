import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Facture} from "./models/facture";
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FacturesService {
    private baseUrl = "http://localhost:8080";

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
        const paidFacturesUrl = this.baseUrl + `/factures_paye_by_methode_payment/${paymentMethod}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getPaidFacturesWithRangeAmount(max: number) {
        const paidFacturesUrl = this.baseUrl + `/montant/0/${max}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getPaidFacturesWithRangeDate(start: string, end: string) {
        const paidFacturesUrl = this.baseUrl + `/date/${start}/${end}`;
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getUnpaidFactures() {
        const paidFacturesUrl = this.baseUrl + "/non_paye";
        return this.http.get<Array<Facture>>(paidFacturesUrl)
    }

    getNumberOfUnpaidFactures() {
        const paidFacturesUrl = this.baseUrl + "/count_facture_non_paye";
        return this.http.get<number>(paidFacturesUrl)
    }

    makeReglement(factures: Array<Facture>): Observable<Array<Facture>> {
        const endpoint = this.baseUrl + "/set_factures";
        return this.http.post<Array<Facture>>(endpoint, factures);
    }
}
