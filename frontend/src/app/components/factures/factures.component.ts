import {Component, OnInit} from '@angular/core';
import {FacturesService} from "../../services/factures.service";
import { ReglementsService } from 'src/app/services/reglements.service';
import { Reglement } from 'src/app/services/models/reglement';
import jsPDF from 'jspdf';


@Component({
    selector: 'factures',
    templateUrl: './factures.component.html',
    styleUrls: ['./factures.component.css']
})
export class FacturesComponent implements OnInit {
    //variables
    reglements: Reglement[] = [];
    defaultStartDate: any;
    defaultEndDate: any;
    unpaidFacturesNumber: number = 0
    amountRangeValue: any;
    selectedTransactionType = 'tous'
    showModal = false;


    constructor(private service: FacturesService , private serviceRegl : ReglementsService  ) {}

    ngOnInit(): void {
        this.paidFactures()
        this.unPaidFacturesNumber()
    }

    generatePDF(reglement: Reglement) {
        const doc = new jsPDF();

        // Add header
        doc.setFontSize(18);
        doc.text('Invoice', 70, 10);

        // Add invoice details
        doc.setFontSize(12);
        doc.text('Invoice Number:', 10, 20);
        doc.text('Date:', 10, 30);
        doc.text('Customer:', 10, 40);

        // Add dynamic data
        doc.setFont('helvetica', 'normal');
        doc.text(`RGL_${reglement.num_reglement} - ${reglement.etat}`, 50, 20);
        doc.text(`${reglement.date_paiement}`, 50, 30);
        // Assuming there is a customer field in Reglement, replace 'John Doe' with the actual property


        // Add table header
        const headers = ['Description', 'Quantity', 'Unit Price', 'Total'];

        // Set initial y-position for the table
        let yPos = 60;

        // Draw table headers
        headers.forEach((header, index) => {
            doc.text(header, 10 + index * 40, yPos);
        });

        // Draw table row for the provided Reglement
        yPos += 10;
        const row = [
            `RGL_${reglement.num_reglement} - ${reglement.etat}`,
            '1', // Assuming quantity is always 1
            `$${reglement.montant}`, // Assuming montant is the unit price
            `$${reglement.montant}` // Assuming montant is the total
        ];

        row.forEach((item, colIndex) => {
            doc.text(item, 10 + colIndex * 40, yPos);
        });

        // Add total
        const total = reglement.montant;
        yPos += 10;
        doc.text(`Total: $${total}`, 150, yPos);

        // Add footer
        doc.setFontSize(10);
        doc.text('Thank you for your business!', 70, yPos + 10);

        const pdfOutput = doc.output();  // No need to specify 'dataurl'
        window.open(URL.createObjectURL(new Blob([pdfOutput], { type: 'application/pdf' })), '_blank');
    }


    onDatesChange() {
        this.getFacturesByDateRange(this.defaultStartDate , this.defaultEndDate)
    }
    getFacturesByDateRange(start: string, end: string) {
        this.serviceRegl.getPaidFacturesWithRangeDate(start, end).subscribe({
            next: (value: Array<Reglement>) => {
                this.reglements = value
            },
            error: (error) => {
                console.error("Error getting factures : ", error);
            }
        });

    }// filtrage par date


    onTransactionTypeChange() {
        if (this.selectedTransactionType === 'unpaid') {
            this.getFacturesByPaymentMethod('Carte Bancaire');
        } else if (this.selectedTransactionType === 'paid') {
            this.getFacturesByPaymentMethod('En Espece');
        } else {
            this.paidFactures()
        }
    }

    getFacturesByPaymentMethod(paymentMethod: String) {
        this.serviceRegl.getPaidFacturesWithPaymentMethod(paymentMethod).subscribe({
            next: (value: Array<Reglement>) => {
                this.reglements = value
            },
            error: (error) => {
                console.error("Error getting factures:", error);
            }
        });

    }//filtrage par methode de payment


    updateRangeLabel() {
        const amountRange = document.getElementById('amountRange') as HTMLInputElement;
        const rangeLabel = document.getElementById('rangeLabel');

        if (amountRange && rangeLabel) {
            rangeLabel.textContent = `Entre 0 et ${amountRange.value}`;
            let maxAmount: number = parseInt(amountRange.value);
            this.getFacturesByAmountRange(maxAmount)
        }
    }
    getFacturesByAmountRange(max: number) {
        this.serviceRegl.getPaidFacturesWithRangeAmount(max).subscribe({
            next: (value: Array<Reglement>) => {
                this.reglements = value
            },
            error: (error) => {
                console.error("Error getting factures:", error);
            }
        });

    }// filtrage par intervale de prix






    unPaidFacturesNumber() {
        this.service.getNumberOfUnpaidFactures().subscribe({
            next: value => {
                this.unpaidFacturesNumber = value
            },
            error: (error) => {
                console.error("Error making reglement:", error);
            }
        });
    }//nombre des factures non payé


    paidFactures() {
        this.serviceRegl.getAllReglements().subscribe({
            next: value => {
                this.reglements = value
            }
        })
    }// factures payé




    openModal() {
        this.showModal = true;
    }//page modale de demande de payment
    closeModal() {
        this.showModal = false;
        this.ngOnInit()
    }//page modale de demande de payment





}
