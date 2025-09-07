package com.saucedemo.page;

import com.microsoft.playwright.Page;

public class CheckoutCompletePage {
    private final Page page;
    public CheckoutCompletePage(Page page) { this.page = page; }

    public String header() {
        return page.locator(".complete-header").textContent().trim();
    }
}
