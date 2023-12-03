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
  somme = 0;

  constructor(private service: FacturesService) {
  }

  calculSomme(){
    for (let i = 0; i < this.factures.length; i++) {
      if (this.factures[i].isSelected)
      this.somme += this.factures[i].montant_total!;
    }
    alert(this.somme)
    this.somme=0
  }
  updateSubmitButtonState() {
    this.isSubmitButtonEnabled = this.factures.some(facture => facture.isSelected);
  }


  ngOnInit(): void {
    this.service.getAllFactures().subscribe({
      next: value => {
        this.factures = value

      }
    })
  }

}
