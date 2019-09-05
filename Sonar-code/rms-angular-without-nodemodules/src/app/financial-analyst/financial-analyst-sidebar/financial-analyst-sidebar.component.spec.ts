import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialAnalystSidebarComponent } from './financial-analyst-sidebar.component';

describe('FinancialAnalystSidebarComponent', () => {
  let component: FinancialAnalystSidebarComponent;
  let fixture: ComponentFixture<FinancialAnalystSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinancialAnalystSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialAnalystSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
