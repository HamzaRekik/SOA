package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Long> {


    List<Facture> findByEtat(Facture.Etat etat);
}
