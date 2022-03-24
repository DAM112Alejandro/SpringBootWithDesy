import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/classes/libro';
import { LibroService} from '../libro-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-libro-list',
  templateUrl: './libro-list.component.html',
  styleUrls: ['./libro-list.component.css']
})
export class LibroListComponent implements OnInit {

  libros: Libro[];
  updatelibro: Libro;
  deletelibro: Libro;
  constructor(private libroService: LibroService) { }

  ngOnInit(): void {
    this.libroService.findAll().subscribe(data => {
      this.libros = data;
    });
  }

  public getLibros(): void {
    this.libroService.findAll().subscribe(
      (response: Libro[]) => {
        this.libros = response;
        console.log(this.libros);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public AddAutor(addForm: NgForm): void {
    document.getElementById('add-libro-form')?.click();
    this.libroService.addLibro(addForm.value).subscribe(
      (response: Libro) => {
        console.log(response);
        this.getLibros();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  public onUpdateLibro(libro: Libro): void {
    console.log('libroedit', libro);
    
     this.libroService.updateLibro(libro).subscribe(
       (response: Libro) => {
       console.log(response);
       this.getLibros();
      },
       (error: HttpErrorResponse) => {
         alert(error.message);
       }
     );
  }
  public onDeleteAutor(libroid: number): void {
    this.libroService.deleteLibro(libroid).subscribe(
      (response: Libro) => {
        console.log(response);
        this.getLibros();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(libro: Libro, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addLibroModal');
    }
    if (mode === 'edit') {
      this.deletelibro = libro;
      button.setAttribute('data-target', '#updateLibroModal');
    }
    if (mode === 'delete') {
      this.deletelibro = libro;
      button.setAttribute('data-target', '#deleteLibroModal');
    }
    container!.appendChild(button);
    button.click();
  }

}