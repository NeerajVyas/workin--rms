import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDetailsBorrowerDialogComponentComponent } from './view-details-borrower-dialog-component.component';

describe('ViewDetailsBorrowerDialogComponentComponent', () => {
  let component: ViewDetailsBorrowerDialogComponentComponent;
  let fixture: ComponentFixture<ViewDetailsBorrowerDialogComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewDetailsBorrowerDialogComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDetailsBorrowerDialogComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
