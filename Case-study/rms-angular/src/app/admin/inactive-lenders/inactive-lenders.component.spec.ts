import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InactiveLendersComponent } from './inactive-lenders.component';

describe('InactiveLendersComponent', () => {
  let component: InactiveLendersComponent;
  let fixture: ComponentFixture<InactiveLendersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InactiveLendersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InactiveLendersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
