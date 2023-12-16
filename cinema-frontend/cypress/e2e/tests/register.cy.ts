import { getEmailInput, getEmailInputError, getForm, getNameInput, getPasswordInput, getPasswordInputError, getRegisterButton, getRepeatPasswordInput, getRepeatPasswordInputError, getReturn, getSurnameInput } from "../pages/register";
import { generateRandomEmail } from "../util";

describe('Register Page', () => {

    beforeEach(() => {
        cy.visit('http://localhost:4200/auth/register')
    })

    it('should display register form', () => {
        getForm().should('be.visible');
    })

    it('should have correct title', () => {
        cy.title().should('eq', 'CinemaFrontend');
    })

    it(': Return button should redirect to login page', () => {
        getReturn().click();
        cy.url().should('eq', 'http://localhost:4200/auth/login');
    })

    it(': After clicking on and out of empty input fields, the errors should be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().click();
        getForm().click();
        getEmailInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Email is required')
        })

        getPasswordInputError().should('not.exist');
        getPasswordInput().click();
        getForm().click();
        getPasswordInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Password is required')
        })

        getRepeatPasswordInputError().should('not.exist');
        getRepeatPasswordInput().click();
        getForm().click();
        getRepeatPasswordInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Password is required')
        })
    })

    it(': After entering garbage data into input fields, the errors should be displayed', () => {
        getEmailInputError().should('not.exist');
        getEmailInput().type("abc");
        getForm().click();
        getEmailInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Please enter a valid email address')
        })

        getPasswordInputError().should('not.exist');
        getPasswordInput().type("abc");
        getForm().click();
        getPasswordInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Please enter a valid password address')
        })

        getRepeatPasswordInputError().should('not.exist');
        getRepeatPasswordInput().type("abc");
        getForm().click();
        getRepeatPasswordInputError().invoke('text').then((text) => {
            expect(text.trim()).to.equal('Please enter a valid password address')
        })
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

        getRepeatPasswordInputError().should('not.exist');
        getRepeatPasswordInput().type("kicia.?312312312As");
        getForm().click();
        getRepeatPasswordInputError().should('not.exist');
    })

    it(': After entering correct credentials and clicking Register button, we should be registered and redirected to home', () => {
        getNameInput().type("aaa");
        getSurnameInput().type("aaa");
        getEmailInput().type(generateRandomEmail());
        getPasswordInput().type("kicia.?312312312As");
        getRepeatPasswordInput().type("kicia.?312312312As");
        getRegisterButton().click();
        cy.url().should('eq', 'http://localhost:4200/home');
    })

    it(': After entering INcorrect credentials and clicking Register button, we should stay at register page', () => {
        getNameInput().type("aaa");
        getSurnameInput().type("aaa");
        getEmailInput().type("valid@email.address");
        getPasswordInput().type("kicia.?312312312Ab");
        getRepeatPasswordInput().type("kicia.?312312312As");
        getRegisterButton().click();
        cy.url().should('eq', 'http://localhost:4200/auth/register');
    })

})
