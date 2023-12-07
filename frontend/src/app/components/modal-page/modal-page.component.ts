import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FacturesService } from 'src/app/services/factures.service';
import { Facture } from 'src/app/services/models/facture';

import {faCircleInfo, faClose, faCoffee, faMinus, faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-modal-page',
  templateUrl: './modal-page.component.html',
  styleUrls: ['./modal-page.component.css']
})
export class ModalPageComponent implements OnInit{
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


  constructor(private service: FacturesService) {
    const today = new Date();
    this.currentDate = today.toISOString().slice(0, 10);
  }

  ngOnInit(): void {
        this.unpaidFactures();
    }
  unpaidFactures(){
    this.service.getUnpaidFactures().subscribe({
      next: value =>{
        this.factures = value
      }
    })
  }
  onClose() {
    this.closeModal.emit();
  }
  addToPay(fact : Facture){
    this.facturesToPay.push(fact)
    this.factures = this.factures.filter(item => item !== fact)
    this.montantTotal += fact.montant_total ?? 0;
  }
  removeFromPay(facture: Facture): void {
    this.facturesToPay = this.facturesToPay.filter(item => item !== facture);
    this.factures.push(facture);
    this.montantTotal -= facture.montant_total ?? 0;
  }

  isModalOpen = false;

  openModal() {

  }
  openDetailsModal(facture: Facture): void {
    this.facture = facture;
    this.isModalOpen = true;
  }

  closeRecuModal() {
    this.isModalOpen = false;
  }


}
