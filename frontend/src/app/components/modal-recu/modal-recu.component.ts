import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Facture } from 'src/app/services/models/facture';

@Component({
  selector: 'recu',
  templateUrl: './modal-recu.component.html',
  styleUrls: ['./modal-recu.component.css']
})
export class ModalRecuComponent {
  @Input() facture: Facture | undefined
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.closeModal.emit();
  }

  
}
