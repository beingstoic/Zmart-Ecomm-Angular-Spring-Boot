import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListofProductsComponent } from './listof-products.component';

describe('ListofProductsComponent', () => {
  let component: ListofProductsComponent;
  let fixture: ComponentFixture<ListofProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListofProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListofProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
