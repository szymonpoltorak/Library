import { findButtonByTextIn } from "../util";

export function getForm() {
    return cy.get('APP-REGISTER').get('FORM');
}

export function getNameInput() {
    return getForm().get('DIV:nth-child(2)>APP-NAME-FIELD:nth-child(1)>MAT-FORM-FIELD:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(2)>DIV:nth-child(1)>INPUT:nth-child(2)');
}

export function getSurnameInput() {
    return getForm().get('DIV:nth-child(2)>APP-NAME-FIELD:nth-child(2)>MAT-FORM-FIELD:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(2)>DIV:nth-child(1)>INPUT:nth-child(2)');
}

export function getEmailInput() {
    return getForm().get('APP-EMAIL-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV>DIV:nth-child(2)>DIV>INPUT:nth-child(2)');
}

export function getEmailInputError() {
    return getForm().get('APP-EMAIL-FIELD:nth-child(3)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
}

export function getPasswordInput() {
    return getForm().get('DIV:nth-child(4)>APP-PASSWORD-FIELD:nth-child(1)>MAT-FORM-FIELD:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(2)>DIV:nth-child(1)>INPUT:nth-child(2)');
}

export function getPasswordInputError() {
    return getForm().get('DIV:nth-child(4)>APP-PASSWORD-FIELD:nth-child(1)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
}

export function getRepeatPasswordInput() {
    return getForm().get('DIV:nth-child(4)>APP-PASSWORD-FIELD:nth-child(2)>MAT-FORM-FIELD:nth-child(1)>DIV:nth-child(1)>DIV:nth-child(2)>DIV:nth-child(1)>INPUT:nth-child(2)');
}

export function getRepeatPasswordInputError() {
    return getForm().get('DIV:nth-child(4)>APP-PASSWORD-FIELD:nth-child(2)>MAT-FORM-FIELD>DIV:nth-child(2)>DIV>MAT-ERROR');
}

export function getRegisterButton() {
    return findButtonByTextIn(getForm(), "register");
}

export function getReturn() {
    return findButtonByTextIn(getForm(), "return");
}
