package com.saucedemo.steps;

import com.saucedemo.core.TestContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {
    private final TestContext tc;

    public CheckoutSteps(TestContext tc) { this.tc = tc; }

    public void clickCheckout() { tc.page.click("#checkout"); }

    public void fillAndContinue(String first, String last, String zip) {
        tc.page.fill("#first-name", first);
        tc.page.fill("#last-name", last);
        tc.page.fill("#postal-code", zip);
        tc.page.click("#continue");
    }

    public void assertOverviewItems(int expected) {
        int count = tc.page.locator(".cart_item").count();
        assertEquals(expected, count, "Ítems en overview no coinciden");
    }

    public void finish() { tc.page.click("#finish"); }

    public void assertCompleteMessage(String expected) {
        String actual = tc.page.locator(".complete-header").innerText().trim();
        assertEquals(expected, actual);
    }

    public void assertFormError(String expected) {
        String actual = tc.page.locator("[data-test='error']").innerText().trim();
        assertEquals(expected, actual);
    }
}
