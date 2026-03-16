export interface Well {
  id: number;
  name: string; // KAVARNA, KALIAKRA
  location: string; // OFFSHORE SUBSEA
  status: string; // ACTIVE, INACTIVE, MAINTENANCE
  
  // Pressure Transmitters
  pt3101: number; // PSIG
  pt1105: number; // Bar(G)
  pt1205: number; // Bar(G)
  pt1401: number; // PSIG
  dp001: number; // Bar
  
  // Valves
  sdv3001: string;
  sdv4001: string;
  sdv1203: number; // Bar(G)
  
  // Valve Positions
  zt1100: number; // %
  zt1200: number; // %
  
  // Vibration
  v460Vibration: number;
  v410Vibration: number;
  
  // Compressors
  c460: number;
  c410: number;
  
  // Flow
  currentDayTotalisedFlow: number; // RM3
  previousDayTotalisedFlow: number; // RM3
  
  // Temperature
  tt1100: number; // DegC
  
  // Bypass
  pt1100lltrpBypass: boolean;
  pt1200lltrpBypass: boolean;
  bypassTimeSeconds: number;
}