import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Well } from '../models/well.model';

@Injectable({
  providedIn: 'root'
})
export class WellService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAllWells(): Observable<Well[]> {
    return this.http.get<Well[]>(`${this.apiUrl}/wells`);
  }

  getWellById(id: number): Observable<Well> {
    return this.http.get<Well>(`${this.apiUrl}/wells/${id}`);
  }

  getWellByName(name: string): Observable<Well> {
    return this.http.get<Well>(`${this.apiUrl}/wells/name/${name}`);
  }
}