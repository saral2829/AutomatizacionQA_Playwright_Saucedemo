package com.saucedemo.stepsdefinitions;

import com.saucedemo.core.TestContext;
import com.saucedemo.steps.LoginSteps;
import io.cucumber.java.en.*;

public class LoginStepsDef {

    private final LoginSteps login;

    public LoginStepsDef(TestContext tc) {
        this.login = new LoginSteps(tc);
    }

    @Given("el usuario abre la página de login")
    public void elUsuarioAbreLaPáginaDeLogin() {
        login.abrirLogin();
    }

    @When("me logeo con usuario {string} y password {string}")
    public void meLogeoConUsuarioYPassword(String user, String pass) {
        login.loginCon(user, pass);
    }

    @Then("debería ver la página de productos")
    public void deberíaVerLaPáginaDeProductos() {
        login.validarEnProductos();
    }

    @Then("debería ver el mensaje de error {string}")
    public void deberíaVerElMensajeDeError(String msg) {
        login.validarMensajeError(msg);
    }

    @And("debería permanecer en la pantalla de login")
    public void deberíaPermanecerEnLaPantallaDeLogin() {
        login.validarSigueEnLogin();
    }
}
