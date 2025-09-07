package com.saucedemo.page;

import com.microsoft.playwright.Page;

public class CheckoutOverviewPage {
    private final Page page;
    public CheckoutOverviewPage(Page page) { this.page = page; }

    public int itemsCount() {
        return page.locator(".cart_item").count();
    }

    public void finish() { page.click("#finish"); }
}
