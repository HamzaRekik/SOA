package com.springboot.reglement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Facture {


    public enum Etat {

        NON_PAYEE,
        PAYEE
    }

    @Id
    @GeneratedValue
    private long id;

    private String methodePayment;

    private String num_facture;

    private long montantTotal;

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    private Etat etat ;

    private LocalDate dateTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Reglement reglement;

    public Facture() {
    }

    public Facture(long id, String methode_payment, String num_facture, long montant_total, Etat etat, LocalDate date_transaction, Reglement reglement) {
        this.id = id;
        this.methodePayment = methode_payment;
        this.num_facture = num_facture;
        this.montantTotal = montant_total;
        this.etat = etat;
        this.dateTransaction = date_transaction;
        this.reglement = reglement;
    }


    public String getMethode_payment() {
        return methodePayment;
    }

    public void setMethode_payment(String methode_payment) {
        this.methodePayment = methode_payment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNum_facture() {
        return num_facture;
    }

    public void setNum_facture(String num_facture) {
        this.num_facture = num_facture;
    }

    public long getMontant_total() {
        return montantTotal;
    }

    public void setMontant_total(long montant_total) {
        this.montantTotal = montant_total;
    }

    public LocalDate getDate_transaction() {
        return dateTransaction;
    }

    public void setDate_transaction(LocalDate date_transaction) {
        this.dateTransaction = date_transaction;
    }
    public Reglement getReglement() {
        return reglement;
    }

    public void setReglement(Reglement reglement) {
        this.reglement = reglement;
    }


    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", num_facture='" + num_facture + '\'' +
                ", montant_total=" + montantTotal +
                ", date_transaction=" + dateTransaction +
                '}';
    }
}
