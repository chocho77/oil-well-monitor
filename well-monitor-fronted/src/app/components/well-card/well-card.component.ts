import { Component, Input, OnInit } from '@angular/core';
import { Well } from '../../models/well.model';
import { WellService } from '../../services/well.service';

@Component({
  selector: 'app-well-card',
  standalone:true,
  templateUrl: './well-card.component.html',
  styleUrls: ['./well-card.component.css']
})
export class WellCardComponent implements OnInit {
  @Input() wellName: string = '';
  @Input() well: Well | null = null;

  constructor(private wellService: WellService) { }

  ngOnInit(): void {
    this.wellService.getWellByName(this.wellName).subscribe({
      next: (data) => {
        this.well = data;
      },
      error: (error) => {
        console.error('Error loading well data:', error);
      }
    });
  }

  formatNumber(value: number | undefined): string {
  if (value === undefined || value === null) return '---';
  return value.toLocaleString('en-US', { 
    minimumFractionDigits: 1,
    maximumFractionDigits: 1 
  });
}

formatFlow(value: number | undefined): string {
  if (value === undefined || value === null) return '---';
  if (value === 0) return '0';
  return value.toLocaleString('en-US');
}
}
