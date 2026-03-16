import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AlarmTableComponent } from './components/alarm-table/alarm-table.component';
import { WellCardComponent } from './components/well-card/well-card.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, 
            BrowserModule,
            HttpClientModule, 
            NavigationComponent, 
            AlarmTableComponent,
            DashboardComponent,
            WellCardComponent,

  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'well-monitor-fronted';
}
