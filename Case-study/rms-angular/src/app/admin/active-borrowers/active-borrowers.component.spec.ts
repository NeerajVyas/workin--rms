import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveBorrowersComponent } from './active-borrowers.component';

describe('ActiveBorrowersComponent', () => {
  let component: ActiveBorrowersComponent;
  let fixture: ComponentFixture<ActiveBorrowersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActiveBorrowersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveBorrowersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
