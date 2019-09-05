import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDetailsDialougComponent } from './view-details-dialoug.component';

describe('ViewDetailsDialougComponent', () => {
  let component: ViewDetailsDialougComponent;
  let fixture: ComponentFixture<ViewDetailsDialougComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewDetailsDialougComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDetailsDialougComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
