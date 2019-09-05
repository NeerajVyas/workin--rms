import { TestBed } from '@angular/core/testing';

import { FinancialAnalystService } from './financial-analyst.service';

describe('FinancialAnalystService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FinancialAnalystService = TestBed.get(FinancialAnalystService);
    expect(service).toBeTruthy();
  });
});
