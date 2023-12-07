package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Long> {
    List<Facture> findByEtat(Facture.Etat etat);
    long countByEtat(Facture.Etat etat);
    List<Facture> findByMontantTotalBetweenAndEtat(double minAmount, double maxAmount , Facture.Etat etat);
    List<Facture> findByDateTransactionBetweenAndEtat(LocalDate start , LocalDate end , Facture.Etat etat);

    List<Facture> findByEtatAndMethodePayment(Facture.Etat etat, String methodePayment);

}
