package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
