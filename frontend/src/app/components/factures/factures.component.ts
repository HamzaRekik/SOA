import {Component, Input, OnInit} from '@angular/core';
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

  constructor(private service: FacturesService) {
  }

  calculSomme(){
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

  paidFactures(){
    this.service.getPaidFactures().subscribe({
      next: value =>{
        this.factures = value
      }
    })
  }

  unpaidFactures(){
    this.service.getUnpaidFactures().subscribe({
      next: value =>{
        this.factures = value
      }
    })
  }

  allFactures(){
    this.service.getAllFactures().subscribe({
      next: value => {
        this.factures = value
      }
    })
  }
  showModal = false;
  modalTitle = 'Sample Modal';
  modalContent = 'This is a sample modal content.';

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    // Do something when modal is closed
    console.log('Modal closed');
  }


  ngOnInit(): void {
    this.allFactures()
  }

}
