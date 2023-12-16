export function findButtonByText(buttonText: any) {
    return cy.contains('button', buttonText, { matchCase: false });
}