import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminViewFinancialAnalystComponent } from './admin-view-financial-analyst.component';

describe('AdminViewFinancialAnalystComponent', () => {
  let component: AdminViewFinancialAnalystComponent;
  let fixture: ComponentFixture<AdminViewFinancialAnalystComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminViewFinancialAnalystComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminViewFinancialAnalystComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
