import { findButtonByTextIn } from "../util";

export function getTableBody() {
    return cy.get('APP-NAV:nth-child(2)>DIV:nth-child(2)>APP-MOVIELIST:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(1)>TABLE:nth-child(1)>TBODY:nth-child(2)');
}

export function getMoviesCalendar() {
    return cy.get('APP-NAV:nth-child(2)>DIV:nth-child(2)>APP-MOVIELIST:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(2)>MAT-CARD:nth-child(1)>MAT-CALENDAR:nth-child(1)');
}

export function getPageSizeSelector() {
    return cy.get('APP-NAV:nth-child(2)>DIV:nth-child(2)>APP-MOVIELIST:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(1)>MAT-PAGINATOR:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(1)>MAT-FORM-FIELD:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(2)>MAT-SELECT:nth-child(1)');
}

export function getSelectedPageSize(option: number) {
    return cy.get(`HTML>BODY:nth-child(2)>DIV>DIV:nth-child(2)>DIV:nth-child(1)>DIV:nth-child(1)>MAT-OPTION:nth-child(${option})`);
}

export function getYearSelector() {
    return getMoviesCalendar().get('MAT-CALENDAR-HEADER:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(1)>BUTTON:nth-child(1)');
}

export function getDaySelectorTable() {
    return getMoviesCalendar().get('DIV:nth-child(2)>MAT-MONTH-VIEW:nth-child(1)>TABLE:nth-child(1)>TBODY:nth-child(2)');
}

export function getDayButton(day: number) {
    return findButtonByTextIn(getDaySelectorTable(), ""+day);
}

export function getFirstYearInYearSelector() {
    return getMoviesCalendar().get('DIV:nth-child(2)>MAT-MULTI-YEAR-VIEW:nth-child(1)>TABLE:nth-child(1)>TBODY:nth-child(2)>TR:nth-child(1)>TD:nth-child(1)>BUTTON:nth-child(1)');
}

export function getJanuaryInMonthSelector() {
    return getMoviesCalendar().get('DIV:nth-child(2)>MAT-YEAR-VIEW:nth-child(1)>TABLE:nth-child(1)>TBODY:nth-child(2)>TR:nth-child(2)>TD:nth-child(1)>BUTTON:nth-child(1)');
}
