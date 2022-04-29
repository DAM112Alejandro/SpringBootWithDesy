import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { DesyAngularModule } from 'desy-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AutorListComponent } from './autor-list/autor-list.component';
import { CategoriaListComponent } from './categoria-list/categoria-list.component';
import { LibroListComponent } from './libro-list/libro-list.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { EmailListComponent } from './email-list/email-list.component';


@NgModule({
  declarations: [
    AppComponent,
    AutorListComponent,
    CategoriaListComponent,
    LibroListComponent,
    EmailListComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DesyAngularModule, 
    BrowserAnimationsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
