import { findButtonByText, findButtonByTextIn } from "../util";

export function getScreeningsList() {
    return cy.get('APP-NAV:nth-child(2)>DIV:nth-child(2)>APP-MOVIE-DETAILS:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(2)');
}

export function getReturnButton() {
    return findButtonByText("return");
}

export function getAddButton() {
    return findButtonByTextIn(getScreeningsList(), "add");
}
