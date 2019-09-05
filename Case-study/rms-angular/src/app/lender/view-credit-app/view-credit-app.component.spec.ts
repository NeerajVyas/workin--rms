import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCreditAppComponent } from './view-credit-app.component';

describe('ViewCreditAppComponent', () => {
  let component: ViewCreditAppComponent;
  let fixture: ComponentFixture<ViewCreditAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCreditAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCreditAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
