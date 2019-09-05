import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowerHomeTableComponent } from './borrower-home-table.component';

describe('BorrowerHomeTableComponent', () => {
  let component: BorrowerHomeTableComponent;
  let fixture: ComponentFixture<BorrowerHomeTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BorrowerHomeTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowerHomeTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
