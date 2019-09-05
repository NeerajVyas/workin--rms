import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LenderHomeComponent } from './lender-home.component';

describe('LenderHomeComponent', () => {
  let component: LenderHomeComponent;
  let fixture: ComponentFixture<LenderHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LenderHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LenderHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
