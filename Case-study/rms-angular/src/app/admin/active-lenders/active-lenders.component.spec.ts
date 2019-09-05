import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveLendersComponent } from './active-lenders.component';

describe('ActiveLendersComponent', () => {
  let component: ActiveLendersComponent;
  let fixture: ComponentFixture<ActiveLendersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActiveLendersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveLendersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
