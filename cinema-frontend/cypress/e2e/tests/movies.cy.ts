import { getHomeButton, getLogoutButton, getMoviesButton } from "../pages/home";
import { loginAsUser } from "../pages/login";
import { getDayButton, getFirstYearInYearSelector, getJanuaryInMonthSelector, getPageSizeSelector, getSelectedPageSize as getSelectedPageSizeOption, getTableBody, getYearSelector } from "../pages/movies";

describe('Home Page', () => {
    
    beforeEach(() => {
        loginAsUser();
        getMoviesButton().click();
    })

    it(': After start we should be at movies page', () => {
        cy.url().should('eq', 'http://localhost:4200/home/movies');
    })

    it(': Today should be full list of movies', () => {
        getTableBody().invoke('children').then((children) => {
            expect(children.length).eq(5)
        })
    })

    it(': Table length should increse when increasing page size', () => {
        getTableBody().invoke('children').then((children) => {
            const initialLength = children.length;
            getPageSizeSelector().click();
            getSelectedPageSizeOption(2).click();
            getTableBody().invoke('children').then((newchildren) => {
                expect(newchildren.length).gt(initialLength)
            })
        })
    })

    it(': The should be no movies in the past', () => {
        getYearSelector().click();
        getFirstYearInYearSelector().click();
        getJanuaryInMonthSelector().click();
        getDayButton(13).click();
        getTableBody().invoke('children').then((children) => {
            expect(children.length).eq(0)
        })
    })

})