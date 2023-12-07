import {Component, OnInit} from '@angular/core';
import {Facture} from "../../services/models/facture";
import {FacturesService} from "../../services/factures.service";

@Component({
    selector: 'factures',
    templateUrl: './factures.component.html',
    styleUrls: ['./factures.component.css']
})
export class FacturesComponent implements OnInit {

    isSubmitButtonEnabled = false;
    factures: Facture[] = [];
    factToRegl: Facture[] = [];
    defaultStartDate: any;
    defaultEndDate: any;
    unpaidFacturesNumber: number = 0

    constructor(private service: FacturesService) {
    }

    ngOnInit(): void {
        this.paidFactures()
        this.unPaidFacturesNumber()
    }

    onDatesChange() {
        this.getFacturesByDateRange(this.defaultStartDate , this.defaultEndDate)
    }
    calculSomme() {
        for (let i = 0; i < this.factures.length; i++) {
            if (this.factures[i].isSelected)
                this.factToRegl.push(this.factures[i])
        }
        console.log(this.factToRegl)
        this.saveReglement()
        this.factToRegl = []
    }

    updateSubmitButtonState() {
        this.isSubmitButtonEnabled = this.factures.some(facture => facture.isSelected);
    }

    getFacturesByPaymentMethod(paymentMethod: String) {
        this.service.getPaidFacturesWithPaymentMethod(paymentMethod).subscribe({
            next: (value: Array<Facture>) => {
                this.factures = value
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });

    }

    getFacturesByAmountRange(max: number) {
        this.service.getPaidFacturesWithRangeAmount(max).subscribe({
            next: (value: Array<Facture>) => {
                this.factures = value
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });

    }

    getFacturesByDateRange(start: string, end: string) {
        this.service.getPaidFacturesWithRangeDate(start, end).subscribe({
            next: (value: Array<Facture>) => {
                this.factures = value
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });

    }


    saveReglement(): void {
        this.service.makeReglement(this.factToRegl).subscribe({
            next: (value: Array<Facture>) => {
                this.updateSubmitButtonState();
                this.ngOnInit();
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });
    }

    unPaidFacturesNumber() {
        this.service.getNumberOfUnpaidFactures().subscribe({
            next: value => {
                this.unpaidFacturesNumber = value
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });
    }

    amountRangeValue: any;

    updateRangeLabel() {
        const amountRange = document.getElementById('amountRange') as HTMLInputElement;
        const rangeLabel = document.getElementById('rangeLabel');

        if (amountRange && rangeLabel) {
            rangeLabel.textContent = `Entre 0 et ${amountRange.value}`;
            let maxAmount: number = parseInt(amountRange.value);
            this.getFacturesByAmountRange(maxAmount)
        }
    }

    paidFactures() {
        this.service.getPaidFactures().subscribe({
            next: value => {
                this.factures = value
            }
        })
    }

    unpaidFactures() {
        this.service.getUnpaidFactures().subscribe({
            next: value => {
                this.factures = value
            }
        })
    }

    allFactures() {
        this.service.getAllFactures().subscribe({
            next: value => {
                this.factures = value
            }
        })
    }

    showModal = false;


    openModal() {
        this.showModal = true;
    }

    closeModal() {
        this.showModal = false;
        // Do something when modal is closed
        console.log('Modal closed');
    }


    selectedTransactionType: string = '';


    onTransactionTypeChange() {

        if (this.selectedTransactionType === 'unpaid') {
            this.getFacturesByPaymentMethod('Carte Bancaire');
        } else if (this.selectedTransactionType === 'paid') {
            this.getFacturesByPaymentMethod('En Espece');
        } else {
            this.paidFactures()
        }
    }


}
