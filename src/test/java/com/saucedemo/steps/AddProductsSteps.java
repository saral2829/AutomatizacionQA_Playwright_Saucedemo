package com.saucedemo.steps;

import com.saucedemo.core.TestContext;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductsSteps {

    private final TestContext tc;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public AddProductsSteps(TestContext tc) {
        this.tc = tc;
        this.loginPage = new LoginPage(tc.page);
        this.productsPage = new ProductsPage(tc.page);
    }

    // ✅ Login con credenciales dinámicas (para: "el usuario se encuentra logeado como {string} y {string}")
    public void loginWith(String username, String password) {
        loginPage.open(tc.baseUrl);
        loginPage.login(username, password);
        assertTrue(loginPage.isProductsPage(), "Login falló");
    }

    // (tu helper existente, por si lo sigues usando en otros steps)
    public void loginStandardUser() {
        loginPage.open(tc.baseUrl);
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(loginPage.isProductsPage(), "Login falló");
    }

    // ✅ Agregar múltiples productos por nombre separados con ';'
    public void addProductsByNames(String namesSeparatedBySemicolon) {
        String[] names = namesSeparatedBySemicolon.split(";");
        for (String raw : names) {
            String name = raw.trim();
            if (!name.isEmpty()) {
                productsPage.addProduct(name);
            }
        }
    }

    // (tu helper existente, por si lo sigues usando en otros steps)
    public void agregarDosProductos() {
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addProduct("Sauce Labs Bike Light");
    }

    // ✅ Obtener el badge del carrito
    public int cartBadgeCount() {
        return productsPage.cartBadgeCount();
    }

    // (tu helper existente, por si lo sigues usando en otros steps)
    public void validarBadgeCarrito(int esperado) {
        assertEquals(esperado, productsPage.cartBadgeCount(), "Cantidad en badge incorrecta");
    }

    public void irAlCarrito() {
        productsPage.openCart();
    }
}
