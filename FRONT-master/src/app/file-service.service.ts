import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({ providedIn: 'root' })
export class FileServiceService {

  private download: string;
  
  constructor(private http: HttpClient) {
    this.download=environment.apiBaseUrl;
  }

  public downloadFile(): any {
		return this.http.get(`${this.download}/excel/download`, {responseType: 'blob'});
  }
   
}