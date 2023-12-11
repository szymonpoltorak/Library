import { Component, inject } from '@angular/core';
import { UtilService } from "@utils/util.service";

@Component({
    selector: 'app-nav',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent {
    //TODO: Injectowanie przez konstruktor
    utilService = inject(UtilService)

    logout() {
        this.utilService.clearStorage();
    }
}
