import { loginAsAdmin, loginAsUser } from "../pages/login";
import { getAddButton, getReturnButton, getScreeningsList } from "../pages/movie_details";

describe('Home Page', () => {

    it(': Return button should redirect to movies page', () => {
        loginAsUser();
        cy.url().should('eq', 'http://localhost:4200/home');
        cy.visit('http://localhost:4200/home/movie_details?id=3');
        getReturnButton().click()
        cy.url().should('eq', 'http://localhost:4200/home/movies');
    })

    it(': User should NOT see screenings adding tab', () => {
        loginAsUser();
        cy.url().should('eq', 'http://localhost:4200/home');
        cy.visit('http://localhost:4200/home/movie_details?id=3');
        getAddButton().should('not.be.visible');
    })

    it(': Admin should not see screenings adding tab', () => {
        loginAsAdmin();
        cy.url().should('eq', 'http://localhost:4200/home');
        cy.visit('http://localhost:4200/home/movie_details?id=3');
        getAddButton().should('be.visible');
    })

    it(': When admin add a screening, the screening list size should increase', () => {
        loginAsAdmin();
        cy.url().should('eq', 'http://localhost:4200/home');
        cy.visit('http://localhost:4200/home/movie_details?id=3');
        cy.wait(800);
        getScreeningsList().invoke('children').then((children) => {
            const initialSize = children.length;
            getAddButton().click();
            cy.wait(800);
            getScreeningsList().invoke('children').then((newchildren) => {
                expect(newchildren.length).eq(initialSize + 1);
            })
        })
    })

})