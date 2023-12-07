import { Facture } from "./facture";

export interface Reglement{
  id? : number ,
  num_reglement? : string ,
  montant?: number,
  etat? : string,
  methode_payment? : string,
  date_paiement? : string,
  factures? : Array<Facture>
}
