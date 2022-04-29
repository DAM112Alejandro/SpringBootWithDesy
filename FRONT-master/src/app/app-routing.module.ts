import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AutorListComponent } from './autor-list/autor-list.component';
import { CategoriaListComponent } from './categoria-list/categoria-list.component';
import { EmailListComponent } from './email-list/email-list.component';

import { LibroListComponent } from './libro-list/libro-list.component';

const routerOptions: ExtraOptions = {
  useHash: false,
  anchorScrolling: 'enabled',

};

const routes: Routes = [
  {path: 'autor/all', component: AutorListComponent},
  {path: 'categoria/all', component: CategoriaListComponent},
  {path: 'libro/all', component: LibroListComponent},
  {path: 'email/enviar', component: EmailListComponent},
  {path: 'excel/download', component: AppComponent}

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }