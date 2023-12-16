import { findButtonByTextIn } from "../util";

export function getForm() {
    return cy.get('APP-LOGIN').get('FORM');
}

export function getEmailInput() {
    return getForm().get('APP-EMAIL-FIELD:nth-child(2)>MAT-FORM-FIELD>DIV>DIV:nth-child(2)>DIV>INPUT:nth-child(2)');
}

export function getEmailInputError() {
    return getForm().get('APP-EMAIL-FIELD:nth-child(2)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
}

export function getPasswordInput() {
    return getForm().get('APP-PASSWORD-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV>DIV:nth-child(2)>DIV>INPUT:nth-child(2)');
}

export function getPasswordInputError() {
    return getForm().get('APP-PASSWORD-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
}

export function getRegisterButton() {
    return findButtonByTextIn(getForm(), "register");
}

export function getLoginButton() {
    return findButtonByTextIn(getForm(), "login");
}

export function loginAsUser() {
    cy.visit('http://localhost:4200/auth/login')
    getEmailInput().type("user123@mail.pl");
    getPasswordInput().type("user");
    getLoginButton().click();
}

export function loginAsAdmin() {
    cy.visit('http://localhost:4200/auth/login')
    getEmailInput().type("admin123@mail.pl");
    getPasswordInput().type("admin");
    getLoginButton().click();
}