
describe('FeedbackView e2e test suite', () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/feedback");
  })

  it("Submit button is enabled when form is filled out correctly", () => {
    cy.get("#name").type("Svein");
    cy.get("#email").type("example@gmail.com");
    cy.get("#message").type("This is a message");
    cy.get("#submit-button").should("be.enabled");
  });

  it('Submit button is disabled when form is empty', () => {
    cy.clearAllSessionStorage();
    cy.get("#submit-button").should("be.disabled");
  });

  it("Submit button is disabled when name is missing", () => {
    cy.get("#email").type("example@gmail.com");
    cy.get("#message").type("This is a message");
    cy.get("#submit-button").should("be.disabled");
  });

  it("Submit button is disabled when email is missing", () => {
    cy.get("#name").type("Svein");
    cy.get("#message").type("This is a message");
    cy.get("#submit-button").should("be.disabled");
  });

  it("Submit button is disabled when message is empty", () => {
    cy.get("#name").type("Svein");
    cy.get("#email").type("example@gmail.com");
    cy.get("#submit-button").should("be.disabled");
  });

  it("Form should not submit when email is invalid", () => {
    cy.window().then(win => {
      cy.stub(win, 'alert').as('windowAlert');
    });
    cy.get("#name").type("Svein");
    cy.get("#email").type("example");
    cy.get("#message").type("This is a message");
    cy.get("#submit-button").click();
    cy.get('@windowAlert').should('not.be.called');
  });

  it("Successful submission triggers popup", () => {
    cy.window().then(win => {
      cy.stub(win, 'alert').as('windowAlert');
    });

    cy.get("#name").type("Svein");
    cy.get("#email").type("example@gmail.com");
    cy.get("#message").type("This is a message");
    cy.get("#submit-button").click();

    cy.get('@windowAlert').should('be.calledOnceWith');
  })
})