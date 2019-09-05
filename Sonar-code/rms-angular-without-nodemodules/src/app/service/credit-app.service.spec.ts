import { TestBed } from '@angular/core/testing';

import { CreditAppService } from './credit-app.service';

describe('CreditAppService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreditAppService = TestBed.get(CreditAppService);
    expect(service).toBeTruthy();
  });
});
