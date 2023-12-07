export interface Facture {
  id?: number,
  num_facture?: string,
  methode_payment?: string,
  montant_total?: number,
  etat?: string,
  date_transaction?: string,
  isSelected: boolean
}
