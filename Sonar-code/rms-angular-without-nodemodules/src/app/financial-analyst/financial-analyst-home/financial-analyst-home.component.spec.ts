import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialAnalystHomeComponent } from './financial-analyst-home.component';

describe('FinancialAnalystHomeComponent', () => {
  let component: FinancialAnalystHomeComponent;
  let fixture: ComponentFixture<FinancialAnalystHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinancialAnalystHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialAnalystHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
