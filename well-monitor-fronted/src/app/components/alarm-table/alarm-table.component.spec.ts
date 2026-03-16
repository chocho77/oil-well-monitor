import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlarmTableComponent } from './alarm-table.component';

describe('AlarmTableComponent', () => {
  let component: AlarmTableComponent;
  let fixture: ComponentFixture<AlarmTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlarmTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlarmTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
