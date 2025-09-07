package com.saucedemo.page;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;
    public CheckoutPage(Page page) { this.page = page; }

    public void fillYourInformation(String first, String last, String zip) {
        page.fill("#first-name", first == null ? "" : first);
        page.fill("#last-name", last == null ? "" : last);
        page.fill("#postal-code", zip == null ? "" : zip);
        page.click("#continue");
    }

    public String errorMessage() {
        return page.locator("[data-test='error']").textContent();
    }
}
