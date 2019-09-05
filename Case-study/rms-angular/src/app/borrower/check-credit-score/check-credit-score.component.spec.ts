import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckCreditScoreComponent } from './check-credit-score.component';

describe('CheckCreditScoreComponent', () => {
  let component: CheckCreditScoreComponent;
  let fixture: ComponentFixture<CheckCreditScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckCreditScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckCreditScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
