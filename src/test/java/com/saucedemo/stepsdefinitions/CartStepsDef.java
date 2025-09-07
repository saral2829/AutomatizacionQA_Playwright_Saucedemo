package com.saucedemo.stepsdefinitions;

import com.saucedemo.steps.CartSteps;
import io.cucumber.java.en.*;

public class CartStepsDef {

    private final CartSteps cart;

    public CartStepsDef(CartSteps cart) { this.cart = cart; }

    @Given("el usuario inicia sesión como {string} y {string}")
    public void el_usuario_inicia_sesión_como_y(String user, String pass) { cart.login(user, pass); }

    @Given("estoy en la página de productos")
    public void estoy_en_la_página_de_productos() { cart.assertProductsPage(); }

    @Given("el carrito está vacío")
    public void el_carrito_está_vacío() { cart.emptyCartIfNeeded(); }

    //@When("agrego los productos {string}")
    //public void agrego_los_productos(String csv) { cart.addProductsCsv(csv); }

    @When("abro el carrito")
    public void abro_el_carrito() { cart.openCart(); }

    @Then("el carrito debe mostrar {int} artículos")
    public void el_carrito_debe_mostrar_artículos(Integer expected) { cart.assertCartItems(expected); }

    @Then("los nombres en el carrito deben ser {string}")
    public void los_nombres_en_el_carrito_deben_ser(String csv) { cart.assertCartNames(csv); }
}
