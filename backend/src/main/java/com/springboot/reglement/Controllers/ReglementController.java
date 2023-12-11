package com.springboot.reglement.Resources;


import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Entities.ReglementFacture;
import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Repositories.FactureRepository;
import com.springboot.reglement.Repositories.ReglementRepository;
import com.springboot.reglement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.springboot.reglement.Entities.Reglement.generateRandomString;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReglementController {

    private final ReglementRepository reglementRepository;
    private final FactureRepository factureRepository;
    private final UserRepository userRepository;

    @Autowired
    EmailController emailController;

    public ReglementController(ReglementRepository reglementRepository, FactureRepository factureRepository, UserRepository userRepository) {
        this.reglementRepository = reglementRepository;
        this.factureRepository = factureRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all_reglements")
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }

    @GetMapping("/reglements/{id}")
    public List<Reglement> getReglementByUserId(@PathVariable long id) {
        User user = userRepository.findById(id);
        return reglementRepository.findByUser(user);
    }

    @GetMapping("/factures")
    public List<Facture> getAllFactures() {
        Sort sort = Sort.by(Sort.Direction.ASC, "etat");
        return factureRepository.findAll(sort);
    }

    @GetMapping("/non_paye/{id}")
    public List<Facture> getFacturesNonPaye(@PathVariable long id) {
        User user = userRepository.findById(id);
        return factureRepository.findByEtatAndUser(Facture.Etat.NON_PAYEE, user);
    }

    @GetMapping("/paye/{id}")
    public List<Facture> getFacturesPaye(@PathVariable long id) {
        User user = userRepository.findById(id);
        return factureRepository.findByEtatAndUser(Facture.Etat.PAYEE, user);
    }

    @GetMapping("/count_facture_non_paye/{id}")
    public long countFacturesNonPaye(@PathVariable long id) {
        User user = userRepository.findById(id);
        return factureRepository.countByEtatAndUser(Facture.Etat.NON_PAYEE, user);
    }


//    @GetMapping("/factures_paye_by_methode_payment/{methode_payment}")
//    public List<Facture> getFacturesByMethodPayment(@PathVariable String methode_payment) {
//        return factureRepository.findByEtatAndMethodePayment(Facture.Etat.PAYEE, methode_payment);
//    }
//
//    @GetMapping("/montant/{min}/{max}")
//    public List<Facture> getFacturesByMontant(@PathVariable double min, @PathVariable double max) {
//        return factureRepository.findByMontantTotalBetweenAndEtat(min, max, Facture.Etat.PAYEE);
//    }
//
//    @GetMapping("/date/{start}/{end}")
//    public List<Facture> getFacturesByDate(@PathVariable LocalDate start, @PathVariable LocalDate end) {
//        return factureRepository.findByDateTransactionBetweenAndEtat(start, end, Facture.Etat.PAYEE);
//    }

    public Reglement createReglement() {
        Reglement reglement = new Reglement();
        reglement.setNum_reglement(generateRandomString());
        reglement.setEtat("Payé");
        reglement.setDate_paiement(LocalDate.now());
        return reglementRepository.save(reglement);
    }

    @PostMapping("/{id}/payer_factures")
    public void setFactures(@RequestBody List<Facture> factures, @PathVariable long id) {
        User user = userRepository.findById(id);
        long somme = 0L;
        String paymentMethod = "";
        Reglement reglement = createReglement();
        for (Facture facture : factures) {
            facture.setEtat(Facture.Etat.PAYEE);
            facture.setReglement(reglement);
            facture.setUser(user);
            paymentMethod = facture.getMethode_payment();
            somme += facture.getMontant_total();
            factureRepository.save(facture);
        }
        reglement.setMontant(somme);
        reglement.setMethode_payment(paymentMethod);
        reglement.setUser(user);
        reglementRepository.save(reglement);

        emailController.sendEmail(user.getEmail(), "PAYMENT_" + reglement.getNum_reglement(), "Factures Paye avec success");

    }


    @GetMapping("/dateReglement/{id}/{start}/{end}")
    public List<Reglement> getReglementsByDate(@PathVariable LocalDate start, @PathVariable LocalDate end, @PathVariable long id) {
        User user = userRepository.findById(id);
        return reglementRepository.findByDatePaiementBetweenAndUser(start, end, user);
    }

    @GetMapping("/montantReglement/{id}/{min}/{max}")
    public List<Reglement> getReglementsByMontant(@PathVariable double min, @PathVariable double max, @PathVariable long id) {
        User user = userRepository.findById(id);
        return reglementRepository.findByMontantBetweenAndUser(min, max, user);
    }

    @GetMapping("/reglements_by_methode_payment/{id}/{methode_payment}")
    public List<Reglement> getReglementsByMethodPayment(@PathVariable String methode_payment, @PathVariable long id) {
        User user = userRepository.findById(id);
        return reglementRepository.findByMethodePaymentAndUser(methode_payment, user);
    }


}

