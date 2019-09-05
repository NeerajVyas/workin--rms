import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowerHeaderComponent } from './borrower-header.component';

describe('BorrowerHeaderComponent', () => {
  let component: BorrowerHeaderComponent;
  let fixture: ComponentFixture<BorrowerHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BorrowerHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowerHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
