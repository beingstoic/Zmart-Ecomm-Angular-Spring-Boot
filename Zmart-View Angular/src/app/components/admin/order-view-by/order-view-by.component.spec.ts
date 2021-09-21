import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderViewByComponent } from './order-view-by.component';

describe('OrderViewByComponent', () => {
  let component: OrderViewByComponent;
  let fixture: ComponentFixture<OrderViewByComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderViewByComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderViewByComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
