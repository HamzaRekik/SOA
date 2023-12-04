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

    private String num_facture;

    private long montant_total;

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    private Etat etat ;

    private LocalDate date_transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Reglement reglement;

    public Facture() {
    }

    public Facture(long id, String num_facture, long montant_total, LocalDate date_transaction ) {
        this.id = id;
        this.num_facture = num_facture;
        this.montant_total = montant_total;
        this.date_transaction = date_transaction;
        this.etat = Etat.NON_PAYEE;
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
        return montant_total;
    }

    public void setMontant_total(long montant_total) {
        this.montant_total = montant_total;
    }

    public LocalDate getDate_transaction() {
        return date_transaction;
    }

    public void setDate_transaction(LocalDate date_transaction) {
        this.date_transaction = date_transaction;
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
                ", montant_total=" + montant_total +
                ", date_transaction=" + date_transaction +
                '}';
    }
}
