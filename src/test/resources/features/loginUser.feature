@login
Feature: Login en Sauce Demo

  Background:
    Given el usuario abre la página de login

  @smoke
  Scenario: Login exitoso con usuario estándar
    When me logeo con usuario "standard_user" y password "secret_sauce"
    Then debería ver la página de productos

  @negativo
  Scenario: Login fallido con usuario bloqueado
    When me logeo con usuario "locked_out_user" y password "secret_sauce"
    Then debería ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."
    And debería permanecer en la pantalla de login

