import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FacturesService} from 'src/app/services/factures.service';
import {Facture} from 'src/app/services/models/facture';
import { ToastrService } from 'ngx-toastr';

import {faCircleInfo, faClose, faMinus, faPlus} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-modal-page',
    templateUrl: './modal-page.component.html',
    styleUrls: ['./modal-page.component.css']
})
export class ModalPageComponent implements OnInit {
    faMinus = faMinus
    faPlus = faPlus
    faClose = faClose
    faDetail = faCircleInfo
    @Output() closeModal = new EventEmitter();
    currentDate: string;
    paymentMethod: string | undefined;
    description: string | undefined;
    factures: Facture[] = [];
    facturesToPay: Facture[] = [];
    facture: Facture | undefined
    montantTotal: number = 0;
    handler: any = null;
    isModalOpen = false;

    constructor(private service: FacturesService,private toastr: ToastrService) {
        const today = new Date();
        this.currentDate = today.toISOString().slice(0, 10);
        this.loadStripe()
    }

    ngOnInit(): void {
        this.unpaidFactures();
    }

    pay(amount: any) {
        if (this.paymentMethod == 'Carte Bancaire') {
            this.handler.open({
                name: 'Payment Stripe',
                amount: amount * 100
            });
            this.calculSomme()
        }else{
            this.calculSomme()
            this.onClose()
        }

    }

    calculSomme() {
        for (let i = 0; i < this.facturesToPay.length; i++) {
            this.facturesToPay[i].methode_payment = this.paymentMethod
        }
        console.log(this.facturesToPay)
        this.saveReglement()
        this.facturesToPay = []


    }
    loadStripe() {
        if (!window.document.getElementById('stripe-script')) {
            var s = window.document.createElement("script");
            s.id = "stripe-script";
            s.type = "text/javascript";
            s.src = "https://checkout.stripe.com/checkout.js";
            s.onload = () => {
                this.handler = (<any>window).StripeCheckout.configure({
                    key: 'pk_test_51HxRkiCumzEESdU2Z1FzfCVAJyiVHyHifo0GeCMAyzHPFme6v6ahYeYbQPpD9BvXbAacO2yFQ8ETlKjo4pkHSHSh00qKzqUVK9',
                    locale: 'auto',
                    token: function (token: any) {

                    }
                });
            }
            window.document.body.appendChild(s);
        }
    }

    unpaidFactures() {
        this.service.getUnpaidFactures().subscribe({
            next: value => {
                this.factures = value
            }
        })
    }//factures non payé

    addToPay(fact: Facture) {
        this.facturesToPay.push(fact)
        this.factures = this.factures.filter(item => item !== fact)
        this.montantTotal += fact.montant_total ?? 0;
    }

    removeFromPay(facture: Facture): void {
        this.facturesToPay = this.facturesToPay.filter(item => item !== facture);
        this.factures.push(facture);
        this.montantTotal -= facture.montant_total ?? 0;
    }

    saveReglement(): void {
        this.service.makeReglement(this.facturesToPay).subscribe({
            next: (value: Array<Facture>) => {

                this.ngOnInit();
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });
    }// creation de reglement


    onClose() {
        this.closeModal.emit();
        // this.toastr.success('Payment Effectué avec success !', '',{timeOut : 1800 , progressBar : true});
        window.location.reload()
    }

    openDetailsModal(facture: Facture): void {
        this.facture = facture;
        this.isModalOpen = true;
    }

    closeRecuModal() {
        this.isModalOpen = false;
    }













}
