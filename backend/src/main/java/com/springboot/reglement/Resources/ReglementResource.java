package com.springboot.reglement.Resources;


import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Repositories.FactureRepository;
import com.springboot.reglement.Repositories.ReglementRepository;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReglementResource {

    private final ReglementRepository reglementRepository;
    private final FactureRepository factureRepository;

    public ReglementResource(ReglementRepository reglementRepository, FactureRepository factureRepository) {
        this.reglementRepository = reglementRepository;
        this.factureRepository = factureRepository;
    }

    @GetMapping("/reglements")
    public List<Reglement> getAllReglements(){
        return reglementRepository.findAll();
    }

    @GetMapping("/reglment/{id}")
    public Optional<Reglement> getReglementById(@PathVariable long id){
        return reglementRepository.findById(id);
    }

    @GetMapping("/factures")
    public List<Facture> getAllFactures(){
        return factureRepository.findAll();
    }


    public static String generateRandomString() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}


