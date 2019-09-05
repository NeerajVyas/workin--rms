import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFinancialAnalystComponent } from './add-financial-analyst.component';

describe('AddFinancialAnalystComponent', () => {
  let component: AddFinancialAnalystComponent;
  let fixture: ComponentFixture<AddFinancialAnalystComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFinancialAnalystComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFinancialAnalystComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
