import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReceivedCreditAppComponent } from './view-received-credit-app.component';

describe('ViewReceivedCreditAppComponent', () => {
  let component: ViewReceivedCreditAppComponent;
  let fixture: ComponentFixture<ViewReceivedCreditAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewReceivedCreditAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewReceivedCreditAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
