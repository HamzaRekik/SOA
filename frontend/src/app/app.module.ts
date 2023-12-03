import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { FactureComponentComponent } from './components/facture-component/facture-component.component';
import { FacturesComponent } from './components/factures/factures.component';
import {FormsModule} from "@angular/forms";
import { ReglementsComponent } from './components/reglements/reglements.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FactureComponentComponent,
    FacturesComponent,
    ReglementsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
