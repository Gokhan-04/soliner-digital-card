import { Routes } from '@angular/router';
import { CardView } from './pages/card-view/card-view';
import { AdminPage } from './pages/admin/admin';

export const routes: Routes = [
  {
    path: '',
    component: CardView
  },
  {
    path: 'admin',
    component: AdminPage
  }
];
