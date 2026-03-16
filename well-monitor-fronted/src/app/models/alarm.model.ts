export interface Alarm {
  id: number;
  dateTime: Date;
  state: string; // UNACK, ACK
  operator: string;
  priority: number; // 1-5
  comment: string;
  name: string;
  provider: string;
  limitType: string; // LOW, DISCRETE, Dead
  silenced: boolean;
  //well?: Well; // optional
}