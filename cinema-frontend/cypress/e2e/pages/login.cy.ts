import { findButtonByText } from "../util";

describe('Login Page', () => {

    beforeEach(() => {
        cy.visit('http://localhost:4200/auth/login')
    })

    it('should display login form', () => {
        getForm().should('be.visible');
    })

    it('should have correct title', () => {
        cy.title().should('eq', 'CinemaFrontend');
    })

    it(': Register button should redirect to register page', () => {
        findButtonByText("register").click();

        cy.url().should('eq', 'http://localhost:4200/auth/register');
    })

    it(': After clicking on and out of empty input fields, the errors should be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().click();
        getForm().click();
        getEmailInputError().should('exist');
        
        getPasswordInputError().should('not.exist');
        getPasswordInput().click();
        getForm().click();
        getPasswordInputError().should('exist');
    })

    it(': After clicking on and out of empty input fields, the errors should be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().click();
        getForm().click();
        getEmailInputError().should('exist');
        
        getPasswordInputError().should('not.exist');
        getPasswordInput().click();
        getForm().click();
        getPasswordInputError().should('exist');
    })

    it(': After entering garbage data into input fields, the errors should be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().type("abc");
        getForm().click();
        getEmailInputError().should('exist');
        
        getPasswordInputError().should('not.exist');
        getPasswordInput().type("abc");
        getForm().click();
        getPasswordInputError().should('exist');
    })

    it(': After entering correct data into input fields, the errors should NOT be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().type("valid@email.address");
        getForm().click();
        getEmailInputError().should('not.exist');
        
        getPasswordInputError().should('not.exist');
        getPasswordInput().type("kicia.?312312312As");
        getForm().click();
        getPasswordInputError().should('not.exist');
    })

    it(': After entering correct credentials and clicking Login button, we should be logged in and redirected to home', () => {
        getEmailInput().type("user123@mail.pl");
        getPasswordInput().type("user");
        findButtonByText("login").click();
        cy.url().should('eq', 'http://localhost:4200/home');
    })

    it(': After entering INcorrect credentials and clicking Login button, we should stay at login page', () => {
        getEmailInput().type("user123@mail.pl");
        getPasswordInput().type("badpassword");
        findButtonByText("login").click();
        cy.url().should('eq', 'http://localhost:4200/auth/login');
    })

    function getForm() {
        return cy.get('APP-LOGIN').get('FORM');
    }

    function getEmailInput() {
        return getForm().get('APP-EMAIL-FIELD:nth-child(2)>MAT-FORM-FIELD>DIV>DIV:nth-child(2)>DIV>INPUT:nth-child(2)');
    }
    function getEmailInputError() {
        return getForm().get('APP-EMAIL-FIELD:nth-child(2)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
    }

    function getPasswordInput() {
        return getForm().get('APP-PASSWORD-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV>DIV:nth-child(2)>DIV>INPUT:nth-child(2)');
    }
    function getPasswordInputError() {
        return getForm().get('APP-PASSWORD-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
    }

})