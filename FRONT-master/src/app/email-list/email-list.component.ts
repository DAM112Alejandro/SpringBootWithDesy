import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Email } from 'src/classes/email';
import { EmailServiceService } from '../email-service.service';

@Component({
  selector: 'app-email-list',
  templateUrl: './email-list.component.html',
  styleUrls: ['./email-list.component.css']
})
export class EmailListComponent implements OnInit {
  
  email: Email;
  fileArchivo: Blob;

  constructor(private emailService: EmailServiceService) { }

  ngOnInit(): void {
  }
  mostrarAlgo() {

    console.log('fich', this.fileArchivo)

  }

 
  public sendEmail(email: NgForm): void {
    
    this.emailService.sendEmail(email.value, this.fileArchivo).subscribe(
      (response: Email) => {
        console.log(response);
        email.reset();

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        email.reset();
      }
    );
  }
 
}



