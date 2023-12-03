package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReglementRepository extends JpaRepository<Reglement,Long> {
}
