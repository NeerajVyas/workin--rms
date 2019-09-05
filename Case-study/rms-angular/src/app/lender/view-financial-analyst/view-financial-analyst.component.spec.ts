import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFinancialAnalystComponent } from './view-financial-analyst.component';

describe('ViewFinancialAnalystComponent', () => {
  let component: ViewFinancialAnalystComponent;
  let fixture: ComponentFixture<ViewFinancialAnalystComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFinancialAnalystComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFinancialAnalystComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
