import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InactiveBorrowersComponent } from './inactive-borrowers.component';

describe('InactiveBorrowersComponent', () => {
  let component: InactiveBorrowersComponent;
  let fixture: ComponentFixture<InactiveBorrowersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InactiveBorrowersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InactiveBorrowersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
