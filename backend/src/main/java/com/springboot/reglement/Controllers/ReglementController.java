package com.springboot.reglement.Controllers;


import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Repositories.FactureRepository;
import com.springboot.reglement.Repositories.ReglementRepository;
import com.springboot.reglement.Repositories.UserRepository;
import com.springboot.reglement.Services.ReglementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.springboot.reglement.Entities.Reglement.generateRandomString;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReglementController {

    @Autowired
    private ReglementService reglementService;


    @GetMapping("/all_reglements")
    public List<Reglement> getAllReglements() {
        return reglementService.getAllReglements();
    }

    @GetMapping("/reglements/{id}")
    public List<Reglement> getReglementByUserId(@PathVariable long id) {
        return reglementService.getReglementByUserId(id);
    }

    @GetMapping("/dateReglement/{id}/{start}/{end}")
    public List<Reglement> getReglementsByDate(@PathVariable LocalDate start, @PathVariable LocalDate end, @PathVariable long id) {
        return reglementService.getReglementsByDate(start, end, id);
    }

    @GetMapping("/montantReglement/{id}/{min}/{max}")
    public List<Reglement> getReglementsByMontant(@PathVariable double min, @PathVariable double max, @PathVariable long id) {
        return reglementService.getReglementsByMontant(min, max, id);
    }

    @GetMapping("/reglements_by_methode_payment/{id}/{methode_payment}")
    public List<Reglement> getReglementsByMethodPayment(@PathVariable String methode_payment, @PathVariable long id) {
        return reglementService.getReglementsByMethodPayment(methode_payment, id);
    }

    @GetMapping("/factures")
    public List<Facture> getAllFactures() {
        return reglementService.getAllFactures();
    }

    @GetMapping("/non_paye/{id}")
    public List<Facture> getFacturesNonPaye(@PathVariable long id) {
        return reglementService.getFacturesNonPaye(id);
    }

    @GetMapping("/paye/{id}")
    public List<Facture> getFacturesPaye(@PathVariable long id) {
        return reglementService.getFacturesPaye(id);
    }

    @GetMapping("/count_facture_non_paye/{id}")
    public long countFacturesNonPaye(@PathVariable long id) {
        return reglementService.countFacturesNonPaye(id);
    }


    @PostMapping("/{id}/payer_factures")
    public void payerFactures(@RequestBody List<Facture> factures, @PathVariable long id) {
        reglementService.payerFactures(factures,id);
    }


}

