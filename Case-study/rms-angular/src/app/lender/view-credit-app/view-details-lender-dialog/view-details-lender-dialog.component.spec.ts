import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDetailsLenderDialogComponent } from './view-details-lender-dialog.component';

describe('ViewDetailsLenderDialogComponent', () => {
  let component: ViewDetailsLenderDialogComponent;
  let fixture: ComponentFixture<ViewDetailsLenderDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewDetailsLenderDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDetailsLenderDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
