import { NgModule } from '@angular/core';
import {RouterModule, Routes } from '@angular/router';
import { FacturesComponent } from './components/factures/factures.component';
import { ReglementsComponent } from './components/reglements/reglements.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guard/auth.guard';



const routes :Routes =[
  {path : 'historique' , component : FacturesComponent , canActivate : [AuthGuard]},
  {path : 'reglements' , component : ReglementsComponent, canActivate : [AuthGuard]},
  {path : 'login' , component : LoginComponent},
  {path : 'register' , component : RegisterComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full' },

]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
