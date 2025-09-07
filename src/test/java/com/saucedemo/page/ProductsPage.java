package com.saucedemo.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {
    private final Page page;
    public ProductsPage(Page page) { this.page = page; }

    public void addProduct(String name) {
        Locator card = page.locator(".inventory_item:has-text(\""+name+"\")");
        // si ya está en Remove, no hace nada:
        if (card.locator("button:has-text('Remove')").count() == 0) {
            card.locator("button:has-text('Add to cart')").click();
        }
    }

    public void openCart() {
        page.click(".shopping_cart_link");
    }

    public int cartBadgeCount() {
        if (page.locator(".shopping_cart_badge").count() == 0) return 0;
        String txt = page.locator(".shopping_cart_badge").innerText().trim();
        return Integer.parseInt(txt);
    }
}
