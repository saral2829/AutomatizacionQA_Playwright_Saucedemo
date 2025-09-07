@addProducts
Feature: El usuario puede agregar productos desde la pagina productos

  Scenario Outline: Agregar productos por nombre
    Given el usuario se encuentra logeado como "standard_user" y "secret_sauce"
    When agrego los productos "<lista>"
    Then el icono del carrito debe mostrar "<esperado>"

    Examples:
      | lista                                                                  | esperado |
      | Sauce Labs Backpack;Sauce Labs Bike Light                              | 2        |
      | Sauce Labs Fleece Jacket;Sauce Labs Onesie;Sauce Labs Bolt T-Shirt     | 3        |