import { Component } from '@angular/core';
import { UtilService } from "@utils/util.service";

@Component({
    selector: 'app-nav',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent {

    constructor(
        protected readonly utilService: UtilService,
    ) {}

    logout() {
        this.utilService.clearStorage();
    }
}
