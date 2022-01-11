import { TestBed } from '@angular/core/testing';

import { AuthGuradService } from './auth-gurad.service';

describe('AuthGuradService', () => {
  let service: AuthGuradService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuradService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
