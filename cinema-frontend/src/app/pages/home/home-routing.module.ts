import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "@pages/home/home.component";
import { MovielistComponent } from "@pages/home/movielist/movielist.component";
import { MovieDetailsComponent } from './movie-details/movie-details.component';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        children: [
            {
                path: 'movies',
                component: MovielistComponent
            },
        ]
    },
    {
        path: '',
        component: HomeComponent,
        children: [
            {
                path: 'movie_details',
                component: MovieDetailsComponent
            },
        ]
    },
    {
        path: '**',
        redirectTo: ''
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {
}
