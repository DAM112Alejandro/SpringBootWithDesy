import { Component, OnInit } from '@angular/core';
import { AutorService } from '../autor-service.service';
import { Autor } from 'src/classes/autor';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-autor-list',
  templateUrl: './autor-list.component.html',
  styleUrls: ['./autor-list.component.css']
})
export class AutorListComponent implements OnInit {
  public page: number;
  autors: Autor[];
  updateautor:Autor;
  deleteautor:Autor;


  constructor(private autorService: AutorService) {
   }
 

  ngOnInit(): void {
    this.autorService.findAll().subscribe(data => {
      this.autors = data;
    });
  }

  public getAutors(): void {
    this.autorService.findAll().subscribe(
      (response: Autor[]) => {
        this.autors = response;
        console.log(this.autors);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public AddAutor(addForm: NgForm): void {
    document.getElementById('add-autor-form')?.click();
    this.autorService.addAutor(addForm.value).subscribe(
      (response: Autor) => {
        console.log(response);
        this.getAutors();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  public onUpdateautors(autor: Autor): void {
    console.log('autoredit', autor);
    
     this.autorService.updateAutor(autor).subscribe(
       (response: Autor) => {
       console.log(response);
       this.getAutors();
      },
       (error: HttpErrorResponse) => {
         alert(error.message);
       }
     );
  }
  public onDeleteAutor(autorid: string): void {
    this.autorService.deleteAutor(autorid).subscribe(
      (response: Autor) => {
        console.log(response);
        this.getAutors();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(autor: Autor, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addAutorModal');
    }
    if (mode === 'edit') {
      this.updateautor = autor;
      button.setAttribute('data-target', '#updateAutorModal');
    }
    if (mode === 'delete') {
      this.deleteautor = autor;
      button.setAttribute('data-target', '#deleteAutorModal');
    }
    container!.appendChild(button);
    button.click();
  }
  public searchAutores(key: string): void {
    console.log(key);
    const results: Autor[] = [];
    for (const autor of this.autors) {
      if (autor.nombre.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || autor.dni.toLowerCase().indexOf(key.toLowerCase())!==-1
      || autor.apellido1.toLowerCase().indexOf(key.toLowerCase())!==-1 ) {
        results.push(autor);
      }
    }
    this.autors = results;
    if (results.length === 0 || !key) {
      this.getAutors();
    }
  }
  public searchAutoresbyNombre(KeyNombre: string): void {
    console.log(KeyNombre);
    const results: Autor[] = [];
    for (const autor of this.autors) {
      if (autor.nombre.toLowerCase().indexOf(KeyNombre.toLowerCase()) !== -1
      || autor.apellido1.toLowerCase().indexOf(KeyNombre.toLowerCase())!==-1 ) {
        results.push(autor);
      }
    }
    this.autors = results;
    if (results.length === 0 || !KeyNombre) {
      this.getAutors();
    }
  }
  public searchAutoresbyDni(KeyDni: string): void {
    console.log(KeyDni);
    const results: Autor[] = [];
    for (const autor of this.autors) {
      if (autor.dni.toLowerCase().indexOf(KeyDni.toLowerCase()) !== -1) {
        results.push(autor);
      }
    }
    this.autors = results;
    if (results.length === 0 || !KeyDni) {
      this.getAutors();
    }
  }
  public searchAutoresbyTelfono(KeyTel: string): void {
    console.log(KeyTel);
    const results: Autor[] = [];
    for (const autor of this.autors) {
      if (autor.telefono.toLowerCase().indexOf(KeyTel.toLowerCase()) !== -1 ) {
        results.push(autor);
      }
    }
    this.autors = results;
    if (results.length === 0 || !KeyTel) {
      this.getAutors();
    }
  }
  public searchAutoresbyEmail(KeyEM: string): void {
    console.log(KeyEM);
    const results: Autor[] = [];
    for (const autor of this.autors) {
      if (autor.email.toLowerCase().indexOf(KeyEM.toLowerCase()) !== -1
      || autor.apellido1.toLowerCase().indexOf(KeyEM.toLowerCase())!==-1 ) {
        results.push(autor);
      }
    }
    this.autors = results;
    if (results.length === 0 || !KeyEM) {
      this.getAutors();
    }
  }
}


 