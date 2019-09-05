import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousCreditScoreComponent } from './previous-credit-score.component';

describe('PreviousCreditScoreComponent', () => {
  let component: PreviousCreditScoreComponent;
  let fixture: ComponentFixture<PreviousCreditScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreviousCreditScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousCreditScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
