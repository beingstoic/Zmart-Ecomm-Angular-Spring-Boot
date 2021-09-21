import { TestBed } from '@angular/core/testing';

import { ZmartService } from './zmart.service';

describe('ZmartService', () => {
  let service: ZmartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZmartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
