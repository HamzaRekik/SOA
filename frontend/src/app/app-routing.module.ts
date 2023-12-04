import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes } from '@angular/router';
import { FacturesComponent } from './components/factures/factures.component';
import { ReglementsComponent } from './components/reglements/reglements.component';


const routes :Routes =[
  {path : '' , component : FacturesComponent},
  {path : 'reglements' , component : ReglementsComponent},
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
