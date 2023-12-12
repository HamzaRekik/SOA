package com.springboot.reglement.Services;

import com.springboot.reglement.Controllers.EmailController;
import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Repositories.FactureRepository;
import com.springboot.reglement.Repositories.ReglementRepository;
import com.springboot.reglement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

import static com.springboot.reglement.Entities.Reglement.generateRandomString;

@Service
public class ReglementService {
    private final ReglementRepository reglementRepository;
    private final FactureRepository factureRepository;
    private final UserRepository userRepository;

    @Autowired
    EmailController emailController;

    public ReglementService(ReglementRepository reglementRepository, FactureRepository factureRepository, UserRepository userRepository) {
        this.reglementRepository = reglementRepository;
        this.factureRepository = factureRepository;
        this.userRepository = userRepository;
    }

    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }


    public List<Reglement> getReglementByUserId( long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return reglementRepository.findByUser(user);
    }


    public List<Reglement> getReglementsByDate( LocalDate start,  LocalDate end,  long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return reglementRepository.findByDatePaiementBetweenAndUser(start, end, user);
    }


    public List<Reglement> getReglementsByMontant( double min, double max,  long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return reglementRepository.findByMontantBetweenAndUser(min, max, user);
    }


    public List<Reglement> getReglementsByMethodPayment( String methode_payment,  long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return reglementRepository.findByMethodePaymentAndUser(methode_payment, user);
    }


    public List<Facture> getAllFactures() {
        Sort sort = Sort.by(Sort.Direction.ASC, "etat");
        return factureRepository.findAll(sort);
    }


    public List<Facture> getFacturesNonPaye( long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return factureRepository.findByEtatAndUser(Facture.Etat.NON_PAYEE, user);
    }


    public List<Facture> getFacturesPaye( long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return factureRepository.findByEtatAndUser(Facture.Etat.PAYEE, user);
    }


    public long countFacturesNonPaye( long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return factureRepository.countByEtatAndUser(Facture.Etat.NON_PAYEE, user);
    }

    public Reglement createReglement() {
        Reglement reglement = new Reglement();
        reglement.setNum_reglement(generateRandomString());
        reglement.setEtat("Payé");
        reglement.setDate_paiement(LocalDate.now());
        return reglementRepository.save(reglement);
    }


    public void payerFactures( List<Facture> factures,  long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
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

}
