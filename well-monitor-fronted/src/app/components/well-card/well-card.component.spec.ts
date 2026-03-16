import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WellCardComponent } from './well-card.component';

describe('WellCardComponent', () => {
  let component: WellCardComponent;
  let fixture: ComponentFixture<WellCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WellCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WellCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
