package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Long> {
    List<Facture> findByEtatAndUser(Facture.Etat etat , User user);
    long countByEtatAndUser(Facture.Etat etat , User user);

}