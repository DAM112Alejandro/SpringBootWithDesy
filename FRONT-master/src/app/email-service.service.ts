
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Email } from 'src/classes/email';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EmailServiceService {

  private emailUrl: string;
  constructor(private http: HttpClient) {
    this.emailUrl = environment.apiBaseUrl;
  }
  public  sendEmail(email: Email, fileArchivo: Blob): Observable<Email>{

    const formData: FormData = new FormData();

    formData.append('email', JSON.stringify(email));

    formData.append('file', fileArchivo);

    return this.http.post<Email>(`${this.emailUrl}/email/enviar`, formData);

  }


}
