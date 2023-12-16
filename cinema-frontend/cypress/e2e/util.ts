export function findButtonByText(buttonText: any) {
    return cy.contains('button', buttonText, { matchCase: false });
}
export function findButtonByTextIn(ctx: any, buttonText: any) {
    return ctx.contains('button', buttonText, { matchCase: false });
}