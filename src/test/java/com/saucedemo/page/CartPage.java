package com.saucedemo.page;

import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;
    public CartPage(Page page) { this.page = page; }

    public int itemsCount() {
        return page.locator(".cart_item").count();
    }

    public void clickCheckout() {
        page.click("#checkout");
    }
}
