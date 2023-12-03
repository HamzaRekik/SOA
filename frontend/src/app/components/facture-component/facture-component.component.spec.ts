import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FactureComponentComponent } from './facture-component.component';

describe('FactureComponentComponent', () => {
  let component: FactureComponentComponent;
  let fixture: ComponentFixture<FactureComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FactureComponentComponent]
    });
    fixture = TestBed.createComponent(FactureComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
