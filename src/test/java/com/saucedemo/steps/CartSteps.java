package com.saucedemo.steps;

import com.saucedemo.core.TestContext;
import com.microsoft.playwright.Locator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartSteps {
    private final TestContext tc;

    public CartSteps(TestContext tc) { this.tc = tc; }

    public void login(String user, String pass) {
        tc.page.navigate(tc.baseUrl); // https://www.saucedemo.com
        tc.page.fill("#user-name", user);
        tc.page.fill("#password", pass);
        tc.page.click("#login-button");
        assertTrue(tc.page.url().contains("inventory.html"), "No se cargó la página de productos");
    }

    public void assertProductsPage() {
        assertTrue(tc.page.url().contains("inventory.html"), "No estás en inventory.html");
    }

    public void emptyCartIfNeeded() {
        // Abre carrito
        tc.page.click(".shopping_cart_link");
        // Elimina todo si hay items
        List<Locator> removes = tc.page.locator("button:has-text('Remove')").all();
        for (Locator r : removes) r.click();
        // Volver a productos
        if (tc.page.locator("#continue-shopping").isVisible())
            tc.page.click("#continue-shopping");
        assertProductsPage();
    }

    public void addProductsCsv(String csv) {
        for (String name : csv.split("\\s*;\\s*")) {
            Locator item = tc.page.locator(".inventory_item").filter(new Locator.FilterOptions().setHasText(name));
            assertTrue(item.isVisible(), "Producto no visible: " + name);
            // Si ya dice Remove, no agregar de nuevo
            Locator btn = item.locator("button");
            if (btn.innerText().toLowerCase().contains("add")) btn.click();
        }
    }

    public void openCart() { tc.page.click(".shopping_cart_link"); }

    public void assertCartItems(int expected) {
        int count = tc.page.locator(".cart_item").count();
        assertEquals(expected, count, "Cantidad de ítems en carrito incorrecta");
    }

    public void assertCartNames(String csv) {
        List<String> expected = Arrays.asList(csv.split("\\s*;\\s*"));
        List<String> actual = new ArrayList<>(tc.page.locator(".inventory_item_name").allTextContents());
        assertEquals(expected, actual, "Nombres en carrito no coinciden");
    }

    public void addNDefaultProducts(int n) {
        // Productos por defecto (suficiente para tus pruebas)
        String[] defaults = new String[]{
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };
        assertTrue(n <= defaults.length, "n supera lista por defecto");
        for (int i = 0; i < n; i++) {
            addProductsCsv(defaults[i]);
        }
    }
}
