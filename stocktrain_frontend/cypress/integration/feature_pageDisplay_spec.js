describe('Home Page Content Tests', function () {

  it('Has a Login Tab when not logged in', function () {
    cy.contains('Login')
  })

  it('Has a Search Tab', function () {
    cy.contains('Search')
  })

  it('Has an Application Info Tab', function () {
    cy.contains('Stock Train')
  })

  it('Has a Stock Display screen', function () {

  })

  it('Has Stock Prices Displayed', function () {

  })

  // post login tests

  it('Has a Logout Button', function () {

  })

  it('Has Options to Purchase Stocks', function() {

  })

  it('Has an Account Tab', function () {

  })
})