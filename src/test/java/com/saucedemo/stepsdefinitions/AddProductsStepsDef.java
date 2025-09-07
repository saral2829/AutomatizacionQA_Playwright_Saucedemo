package com.saucedemo.stepsdefinitions;

import com.saucedemo.core.TestContext;
import com.saucedemo.steps.AddProductsSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddProductsStepsDef {

    private final AddProductsSteps steps;

    public AddProductsStepsDef(TestContext tc) {
        this.steps = new AddProductsSteps(tc);
    }

    @Given("el usuario se encuentra logeado como {string} y {string}")
    public void elUsuarioSeEncuentraLogeadoComoY(String username, String password) {
        steps.loginWith(username, password);
    }

    @When("agrego los productos {string}")
    public void agregoLosProductos(String productosSeparadosPorPuntoYComa) {
        steps.addProductsByNames(productosSeparadosPorPuntoYComa);
    }

    @Then("el icono del carrito debe mostrar {string}")
    public void elIconoDelCarritoDebeMostrar(String esperado) {
        int actual = steps.cartBadgeCount();
        assertEquals(Integer.parseInt(esperado), actual, "El contador del carrito no coincide");
    }
}
