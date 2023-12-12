package com.springboot.reglement.ControllersTest;

import com.springboot.reglement.Controllers.ReglementController;
import com.springboot.reglement.Entities.Facture;
import com.springboot.reglement.Entities.Reglement;
import com.springboot.reglement.Entities.User;
import com.springboot.reglement.Services.ReglementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ReglementController.class)
class ReglementControllerTest {

    @MockBean
    ReglementService reglementService;

    @Autowired
    MockMvc mockMvc;


    @Test
    void testGetAllReglements() throws Exception {
        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), null),
                new Reglement(2, "Test2", 1000L, "Payé", "Espece", LocalDate.now(), null)
        );
        when(reglementService.getAllReglements()).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all_reglements").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetReglementById() throws Exception {
        User user = new User();
        user.setId(1L);

        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), user),
                new Reglement(2, "Test2", 1000L, "Payé", "Espece", LocalDate.now(), user)
        );


        when(reglementService.getReglementByUserId(1L)).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reglements/{id}", 1L).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetReglementByDate() throws Exception {
        User user = new User();
        user.setId(1L);

        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), user),
                new Reglement(2, "Test2", 1000L, "Payé", "Espece", LocalDate.now(), user)
        );


        when(reglementService.getReglementsByDate(LocalDate.of(2023, 12, 11), LocalDate.now(), 1L)).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dateReglement/{id}/{start}/{end}", 1L, LocalDate.of(2023, 12, 11), LocalDate.now()).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetReglementByMontant() throws Exception {
        User user = new User();
        user.setId(1L);

        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), user),
                new Reglement(2, "Test2", 1000L, "Payé", "Espece", LocalDate.now(), user)
        );


        when(reglementService.getReglementsByMontant(0, 1100, 1L)).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/montantReglement/{id}/{min}/{max}", 1L, 0, 1100).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetReglementParCarteBancaire() throws Exception {
        User user = new User();
        user.setId(1L);

        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), user),
                new Reglement(2, "Test2", 1000L, "Payé", "Carte Bancaire", LocalDate.now(), user)
        );


        when(reglementService.getReglementsByMethodPayment("Carte Bancaire", 1L)).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reglements_by_methode_payment/{id}/{methode_payment}", 1L, "Carte Bancaire").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetReglementParEspece() throws Exception {
        User user = new User();
        user.setId(1L);

        List<Reglement> reglements = Arrays.asList(
                new Reglement(1, "Test1", 1000L, "Payé", "En Espece", LocalDate.now(), user),
                new Reglement(2, "Test2", 1000L, "Payé", "En Espece", LocalDate.now(), user)
        );


        when(reglementService.getReglementsByMethodPayment("En Espece", 1L)).thenReturn(reglements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reglements_by_methode_payment/{id}/{methode_payment}", 1L, "En Espece").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_reglement", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_reglement", is("Test2")));
    }

    @Test
    void testGetAllFactures() throws Exception {
        Reglement reglement = new Reglement();
        reglement.setId(2L);

        List<Facture> factures = Arrays.asList(
                new Facture(1, null, "Test1", 200L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, null),
                new Facture(2, "En Espece", "Test2", 300L, Facture.Etat.PAYEE, LocalDate.now(), reglement, null)

        );


        when(reglementService.getAllFactures()).thenReturn(factures);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/factures").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_facture", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_facture", is("Test2")));
    }

    @Test
    void testFacturesNonPaye() throws Exception {
        User user = new User();
        user.setId(1L);


        List<Facture> factures = Arrays.asList(
                new Facture(1, null, "Test1", 200L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user),
                new Facture(2, null, "Test2", 300L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user)

        );


        when(reglementService.getFacturesNonPaye(1L)).thenReturn(factures);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/non_paye/{id}", 1L).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_facture", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_facture", is("Test2")));
    }
    @Test
    void testFacturesPaye() throws Exception {
        User user = new User();
        user.setId(1L);
        Reglement reglement = new Reglement();
        reglement.setId(2L);

        List<Facture> factures = Arrays.asList(
                new Facture(1, "Carte Bancaire", "Test1", 200L, Facture.Etat.PAYEE, LocalDate.now(), reglement, user),
                new Facture(2, "Carte Bancaire", "Test2", 300L, Facture.Etat.PAYEE, LocalDate.now(), reglement, user)

        );


        when(reglementService.getFacturesPaye(1L)).thenReturn(factures);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paye/{id}", 1L).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].num_facture", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].num_facture", is("Test2")));
    }

    @Test
    void testCountFactureNonPaye() throws Exception {
        User user = new User();
        user.setId(1L);



        List<Facture> factures = Arrays.asList(
                new Facture(1, null, "Test1", 200L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user),
                new Facture(2, null, "Test2", 300L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user),
                new Facture(3, null, "Test3", 300L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user)


        );


        when(reglementService.countFacturesNonPaye(1L)).thenReturn((long) factures.size());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count_facture_non_paye/{id}", 1L).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf((long) factures.size())));

    }


    @Test
    void payerFactures() throws Exception {
        User user = new User();
        user.setId(1L);



        List<Facture> factures = Arrays.asList(
                new Facture(1, null, "Test1", 200L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user),
                new Facture(2, null, "Test2", 300L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user),
                new Facture(3, null, "Test3", 300L, Facture.Etat.NON_PAYEE, LocalDate.now(), null, user)


        );


        when(reglementService.countFacturesNonPaye(1L)).thenReturn((long) factures.size());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count_facture_non_paye/{id}", 1L).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf((long) factures.size())));

    }


}
