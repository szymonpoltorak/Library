import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouterPaths } from "@enums/RouterPaths";
import { LoginComponent } from "./login.component";

const routes: Routes = [
    {
        path: RouterPaths.CURRENT_PATH,
        component: LoginComponent
    },
    {
        path: RouterPaths.REGISTER_FULL_PATH,
        loadChildren: () => import("../register/register.module")
            .then(module => module.RegisterModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LoginRoutingModule {
}
