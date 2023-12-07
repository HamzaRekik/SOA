package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement,Long> {

    List<Reglement> findByMethodePayment(String methodePayment);
    List<Reglement> findByMontantBetween(double minAmount, double maxAmount );
    List<Reglement> findByDatePaiementBetween(LocalDate start , LocalDate end );

}
