export function findButtonByText(buttonText: any) {
    return cy.contains('button', buttonText, { matchCase: false });
}
export function findButtonByTextIn(ctx: any, buttonText: any) {
    return ctx.contains('button', buttonText, { matchCase: false });
}
export function generateRandomEmail() {
    const username = Math.random().toString(36).substring(2, 10);
    const domain = Math.random().toString(36).substring(2, 8);
    const email = `x${username}@x${domain}.com`;
    return email;
}