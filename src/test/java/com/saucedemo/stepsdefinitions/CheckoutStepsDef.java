package com.saucedemo.stepsdefinitions;

import com.saucedemo.steps.CartSteps;
import com.saucedemo.steps.CheckoutSteps;
import io.cucumber.java.en.*;

public class CheckoutStepsDef {

    private final CartSteps cart;
    private final CheckoutSteps co;

    public CheckoutStepsDef(CartSteps cart, CheckoutSteps co) {
        this.cart = cart;
        this.co = co;
    }

    // Background
    @Given("el usuario está logueado como {string} con password {string}")
    public void el_usuario_está_logueado_como_con_password(String user, String pass) { cart.login(user, pass); }

    @Given("el usuario agregó {int} productos al carrito")
    public void el_usuario_agregó_productos_al_carrito(Integer n) { cart.addNDefaultProducts(n); }

    @Given("navega al carrito")
    public void navega_al_carrito() { cart.openCart(); }

    // Flujo de checkout
    @When("hace click en {string}")
    public void hace_click_en(String buttonText) { co.clickCheckout(); } // esperamos "Checkout"

    @When("completa el formulario de envío con {string} {string} {string} y continúa")
    public void completa_formulario_envio_y_continua(String nombre, String apellido, String zip) {
        co.fillAndContinue(nombre, apellido, zip);
    }

    @Then("se muestra la página de resumen con {int} ítems")
    public void resumen_con_items(Integer expected) { co.assertOverviewItems(expected); }

    @When("finaliza la compra")
    public void finaliza_la_compra() { co.finish(); }

    @Then("veo el mensaje de confirmación {string}")
    public void veo_mensaje_confirmacion(String msg) { co.assertCompleteMessage(msg); }

    // Negativos
    @Then("veo el mensaje de error {string}")
    public void veo_mensaje_de_error(String msg) { co.assertFormError(msg); }
}
