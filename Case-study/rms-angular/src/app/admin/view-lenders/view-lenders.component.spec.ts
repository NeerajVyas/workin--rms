import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLendersComponent } from './view-lenders.component';

describe('ViewLendersComponent', () => {
  let component: ViewLendersComponent;
  let fixture: ComponentFixture<ViewLendersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewLendersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLendersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
