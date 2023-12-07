import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FacturesComponent} from './components/factures/factures.component';
import {FormsModule} from "@angular/forms";
import {ReglementsComponent} from './components/reglements/reglements.component';
import {AppRoutingModule} from './app-routing.module';
import {ModalPageComponent} from './components/modal-page/modal-page.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ModalRecuComponent} from './components/modal-recu/modal-recu.component';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';


@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        FacturesComponent,
        ReglementsComponent,
        ModalPageComponent,
        ModalRecuComponent,
    ],
    imports: [
        CommonModule,
        BrowserAnimationsModule, // required animations module
        ToastrModule.forRoot(),
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        FontAwesomeModule,

    ],
    providers: [HttpClient],
    bootstrap: [AppComponent]
})
export class AppModule {
}
