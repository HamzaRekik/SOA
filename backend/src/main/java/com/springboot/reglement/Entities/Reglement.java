package com.springboot.reglement.Entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Reglement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String num_reglement;

    private Long montant;

    private String etat ;

    private String methodePayment;

    private LocalDate datePaiement;

    @OneToMany(mappedBy = "reglement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facture> factures;


    public Reglement() {
    }


    public Reglement(long id, String num_reglement, Long montant, String etat, String methode_payment, LocalDate date_paiement, List<Facture> factures) {
        this.id = id;
        this.num_reglement = num_reglement;
        this.montant = montant;
        this.etat = etat;
        this.methodePayment = methode_payment;
        this.datePaiement = date_paiement;
        this.factures = factures;
    }



    public void setId(long id) {
        this.id = id;
    }

    public String getMethode_payment() {
        return methodePayment;
    }

    public void setMethode_payment(String methode_payment) {
        this.methodePayment = methode_payment;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDate getDate_paiement() {
        return datePaiement;
    }

    public void setDate_paiement(LocalDate date_paiement) {
        this.datePaiement = date_paiement;
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
                ", date_paiement=" + datePaiement +
                '}';
    }
}
