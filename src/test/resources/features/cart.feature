@cart
@screenshot
Feature: Visualizar productos en el carrito de compras

  Scenario Outline: Ver productos agregados en el carrito
    Given el usuario inicia sesión como "<username>" y "<password>"
    And estoy en la página de productos
    And el carrito está vacío
    When agrego los productos "<lista>"
    And abro el carrito
    Then el carrito debe mostrar <esperado> artículos
    And los nombres en el carrito deben ser "<lista>"

    Examples:
      | username      | password     | lista                                                               | esperado |
      | standard_user | secret_sauce | Sauce Labs Backpack;Sauce Labs Bike Light                           | 2        |
      | standard_user | secret_sauce | Sauce Labs Fleece Jacket;Sauce Labs Onesie;Sauce Labs Bolt T-Shirt  | 3        |
