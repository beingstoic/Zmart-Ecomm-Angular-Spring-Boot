import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchOrdersComponent } from './search-orders.component';

describe('SearchOrdersComponent', () => {
  let component: SearchOrdersComponent;
  let fixture: ComponentFixture<SearchOrdersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchOrdersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
