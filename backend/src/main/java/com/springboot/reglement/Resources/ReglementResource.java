package com.springboot.reglement.Resources;


import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Repositories.FactureRepository;
import com.springboot.reglement.Repositories.ReglementRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDate;
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
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }

    @GetMapping("/reglement/{id}")
    public Optional<Reglement> getReglementById(@PathVariable long id) {
        return reglementRepository.findById(id);
    }

    @GetMapping("/factures")
    public List<Facture> getAllFactures() {
        Sort sort = Sort.by(Sort.Direction.ASC, "etat");
        return factureRepository.findAll(sort);
    }

    @GetMapping("/non_paye")
    public List<Facture> getFacturesNonPaye() {
        return factureRepository.findByEtat(Facture.Etat.NON_PAYEE);
    }

    @GetMapping("/count_facture_non_paye")
    public long countFacturesNonPaye() {
        return factureRepository.countByEtat(Facture.Etat.NON_PAYEE);
    }

    @GetMapping("/paye")
    public List<Facture> getFacturesPaye() {
        return factureRepository.findByEtat(Facture.Etat.PAYEE);
    }

    @GetMapping("/factures_paye_by_methode_payment/{methode_payment}")
    public List<Facture> getFacturesByMethodPayment(@PathVariable String methode_payment) {
        return factureRepository.findByEtatAndMethodePayment(Facture.Etat.PAYEE, methode_payment);
    }

    @GetMapping("/montant/{min}/{max}")
    public List<Facture> getFacturesByMontant(@PathVariable double min, @PathVariable double max) {
        return factureRepository.findByMontantTotalBetweenAndEtat(min, max, Facture.Etat.PAYEE);
    }

    @GetMapping("/date/{start}/{end}")
    public List<Facture> getFacturesByDate(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return factureRepository.findByDateTransactionBetweenAndEtat(start, end, Facture.Etat.PAYEE);
    }

    public Reglement createReglement() {
        Reglement reglement = new Reglement();
        reglement.setNum_reglement(generateRandomString());
        reglement.setEtat("Payé");
        reglement.setDate_paiement(LocalDate.now());
        return reglementRepository.save(reglement);
    }

    @PostMapping("/set_factures")
    public void setFactures(@RequestBody List<Facture> factures) {
        long somme = 0L;
        String paymentMethod = "";
        Reglement reglement = createReglement();
        for (Facture facture : factures) {
            facture.setEtat(Facture.Etat.PAYEE);
            facture.setReglement(reglement);
            paymentMethod = facture.getMethode_payment();
            somme += facture.getMontant_total();
            factureRepository.save(facture);
        }
        reglement.setMontant(somme);
        reglement.setMethode_payment(paymentMethod);
        reglementRepository.save(reglement);
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


