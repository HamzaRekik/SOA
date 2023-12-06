import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes } from '@angular/router';
import { FacturesComponent } from './components/factures/factures.component';
import { ReglementsComponent } from './components/reglements/reglements.component';
import { PaymentStripeComponent } from './components/payment-stripe/payment-stripe.component';


const routes :Routes =[
  {path : '' , component : FacturesComponent},
  {path : 'reglements' , component : ReglementsComponent},
  {path : 'stripe' , component : PaymentStripeComponent},
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
