package com.saucedemo.page;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    public LoginPage(Page page) { this.page = page; }

    public void open(String baseUrl) {
        page.navigate(baseUrl);
    }

    public void login(String user, String pass) {
        page.fill("#user-name", user);
        page.fill("#password", pass);
        page.click("#login-button");
    }

    public boolean isProductsPage() {
        return page.url().contains("inventory.html")
                || page.locator(".title:has-text('Products')").isVisible();
    }

    public String errorMessage() {
        return page.locator("[data-test='error']").textContent();
    }
}
