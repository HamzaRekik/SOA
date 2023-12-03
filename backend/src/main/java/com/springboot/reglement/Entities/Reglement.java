package com.springboot.reglement.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Reglement {

    public enum Etat {
        REGLE,
        NON_REGLE,
    }


    @Id
    @GeneratedValue
    private Long id;

    private String num_reglement;

    private Long montant;

    private Etat etat ;

    private LocalDate date_paiement;

    @OneToMany(mappedBy = "reglement")
    private List<Facture> factures;


    public Reglement() {
    }

    public Reglement(Long id, String num_reglement, Long montant, Etat etat, LocalDate date_paiement) {
        this.id = id;
        this.num_reglement = num_reglement;
        this.montant = montant;
        this.etat = etat;
        this.date_paiement = date_paiement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum_reglement() {
        return num_reglement;
    }

    public void setNum_reglement(String num_reglement) {
        this.num_reglement = num_reglement;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public LocalDate getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(LocalDate date_paiement) {
        this.date_paiement = date_paiement;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "id=" + id +
                ", num_reglement='" + num_reglement + '\'' +
                ", montant=" + montant +
                ", etat=" + etat +
                ", date_paiement=" + date_paiement +
                '}';
    }
}
