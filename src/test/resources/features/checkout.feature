@Checkout
Feature: Completar compra

  Background:
    Given el usuario está logueado como "standard_user" con password "secret_sauce"
    And el usuario agregó 2 productos al carrito
    And navega al carrito

  @smoke
  Scenario Outline: Compra exitosa con datos válidos
    When hace click en "Checkout"
    And completa el formulario de envío con "<nombre>" "<apellido>" "<zip>" y continúa
    Then se muestra la página de resumen con 2 ítems
    When finaliza la compra
    Then veo el mensaje de confirmación "Thank you for your order!"

    Examples:
      | nombre | apellido | zip    |
      | Felix  | Garcia   | 040101 |
      | Luis   | Lora     | 150105 |

  @negativo
  Scenario Outline: Validaciones del formulario de envío
    When hace click en "Checkout"
    And completa el formulario de envío con "<nombre>" "<apellido>" "<zip>" y continúa
    Then veo el mensaje de error "<mensaje>"

    Examples:
      | nombre | apellido | zip   | mensaje                        |
      |        | Gomez    | 01001 | Error: First Name is required  |
      | Sara   |          | 01001 | Error: Last Name is required   |
      | Sara   | Gomez    |       | Error: Postal Code is required |
