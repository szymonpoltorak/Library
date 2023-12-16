import { getHomeButton, getLogoutButton, getMoviesButton } from "../pages/home";
import { loginAsUser } from "../pages/login";
import { findButtonByText } from "../util";

describe('Home Page', () => {
    
    beforeEach(() => {
        loginAsUser();
    })

    it(': After login we should be at home page', () => {
        cy.url().should('eq', 'http://localhost:4200/home');
    })

    it(': We should be redirected to login page after clicking Logout button', () => {
        getLogoutButton().click();
        cy.url().should('eq', 'http://localhost:4200/auth/login');
    })

    it(': We should be redirected to movies page after clicking Movies button', () => {
        getMoviesButton().click();
        cy.url().should('eq', 'http://localhost:4200/home/movies');
    })

    it(': We should stay at home page after clicking Home button', () => {
        getHomeButton().click();
        cy.url().should('eq', 'http://localhost:4200/home');
    })

})