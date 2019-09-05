import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPreviousUpdatedCreditAppComponent } from './view-previous-updated-credit-app.component';

describe('ViewPreviousUpdatedCreditAppComponent', () => {
  let component: ViewPreviousUpdatedCreditAppComponent;
  let fixture: ComponentFixture<ViewPreviousUpdatedCreditAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPreviousUpdatedCreditAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPreviousUpdatedCreditAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
