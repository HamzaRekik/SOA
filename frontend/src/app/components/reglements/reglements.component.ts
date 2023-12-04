import { Component, OnInit } from '@angular/core';
import { Reglement } from 'src/app/services/models/reglement';
import { ReglementsService } from 'src/app/services/reglements.service';

@Component({
  selector: 'app-reglements',
  templateUrl: './reglements.component.html',
  styleUrls: ['./reglements.component.css']
})
export class ReglementsComponent implements OnInit{

  reglements : Reglement[] = []
  constructor(private service : ReglementsService) {
  }

  ngOnInit(): void {
       this.service.getAllReglements().subscribe({
         next: value =>{
           this.reglements = value
         }
       })
    }
}
