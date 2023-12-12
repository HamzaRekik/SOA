package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement,Long> {

    List<Reglement> findByMethodePaymentAndUser(String methodePayment , User user);
    List<Reglement> findByUser(User user);
    List<Reglement> findByMontantBetweenAndUser(double minAmount, double maxAmount , User user);
    List<Reglement> findByDatePaiementBetweenAndUser(LocalDate start , LocalDate end , User user );

}