package com.saucedemo.steps;

import com.saucedemo.core.TestContext;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final TestContext tc;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public LoginSteps(TestContext tc) {
        this.tc = tc;
        this.loginPage = new LoginPage(tc.page);
        this.productsPage = new ProductsPage(tc.page);
    }

    public void abrirLogin() {
        loginPage.open(tc.baseUrl);
    }

    public void loginCon(String user, String pass) {
        loginPage.login(user, pass);
    }

    public void validarEnProductos() {
        assertTrue(loginPage.isProductsPage(), "No se visualiza la página de productos");
    }

    public void validarMensajeError(String esperado) {
        assertEquals(esperado.trim(), loginPage.errorMessage().trim());
    }

    public void validarSigueEnLogin() {
        assertFalse(loginPage.isProductsPage(), "No debería avanzar a productos");
    }
}
