import { findButtonByTextIn } from "../util";

export function getToolbar() {
    return cy.get('APP-NAV>MAT-TOOLBAR>MAT-TOOLBAR-ROW');
}

export function getMoviesButton() {
    return getToolbar().get('DIV:nth-child(2)>BUTTON:nth-child(2)');
}

export function getHomeButton() {
    return getToolbar().get('DIV:nth-child(2)>BUTTON:nth-child(1)');
}

export function getLogoutButton() {
    return findButtonByTextIn(getToolbar(), "logout");
}
