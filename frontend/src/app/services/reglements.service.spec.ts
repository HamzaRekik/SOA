import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReglementsService } from './reglements.service';
import { Reglement } from './models/reglement';

describe('ReglementsService', () => {
  let service: ReglementsService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReglementsService]
    });

    service = TestBed.inject(ReglementsService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('la creation de service', () => {
    expect(service).toBeTruthy();
  });

  it('affichage tous les reglements', () => {
    const mockReglements: Reglement[] = [{
      "id": 1,
      "num_reglement": "REG001",
      "montant": 100.50,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    }
    ];
    service.getAllReglements().subscribe(reglements => {
      expect(reglements).toEqual(mockReglements);
    });

    const req = httpTestingController.expectOne('http://localhost:8080/all_reglements');
    expect(req.request.method).toEqual('GET');
    req.flush(mockReglements);
  });

  it('affichage des reglements avec methode de payment', () => {
    const paymentMethod = 'Carte Bancaire';
    const mockReglements: Reglement[] = [{
      "id": 1,
      "num_reglement": "REG001",
      "montant": 100.50,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    }
    ];
    service.getPaidFacturesWithPaymentMethod(paymentMethod).subscribe(reglements => {
      expect(reglements).toEqual(mockReglements);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/reglements_by_methode_payment/${service['userId']}/${paymentMethod}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockReglements);
  });

  it('affichage des reglements avec intervale de montant', () => {
    const maxAmount = 100;
    const mockReglements: Reglement[] =  [{
      "id": 1,
      "num_reglement": "REG001",
      "montant": 90,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    },{
      "id": 2,
      "num_reglement": "REG001",
      "montant": 30,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    }
    ];
    service.getPaidFacturesWithRangeAmount(maxAmount).subscribe(reglements => {
      expect(reglements).toEqual(mockReglements);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/montantReglement/${service['userId']}/0/${maxAmount}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockReglements);
  });

  it('affichage des reglements avec intervale de date', () => {
    const startDate = '2023-01-01';
    const endDate = '2023-12-31';
    const mockReglements: Reglement[] = [{
      "id": 1,
      "num_reglement": "REG001",
      "montant": 90,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    },{
      "id": 2,
      "num_reglement": "REG001",
      "montant": 30,
      "etat": "Payé",
      "methode_payment": "Carte Bancaire",
      "date_paiement": "2023-03-01",

    }
    ];
    service.getPaidFacturesWithRangeDate(startDate, endDate).subscribe(reglements => {
      expect(reglements).toEqual(mockReglements);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/dateReglement/${service['userId']}/${startDate}/${endDate}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockReglements);
  });



});
