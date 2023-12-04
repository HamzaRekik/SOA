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

  ngOnInit(): void {
    this.service.getAllFactures().subscribe({
      next: value => {
        this.factures = value

      }
    })
  }

}
