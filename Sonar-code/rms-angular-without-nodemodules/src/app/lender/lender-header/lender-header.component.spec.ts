import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LenderHeaderComponent } from './lender-header.component';

describe('LenderHeaderComponent', () => {
  let component: LenderHeaderComponent;
  let fixture: ComponentFixture<LenderHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LenderHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LenderHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
