package com.saucedemo.steps;

import com.saucedemo.core.TestContext;
import com.saucedemo.page.CartPage;
import com.saucedemo.page.CheckoutCompletePage;
import com.saucedemo.page.CheckoutOverviewPage;
import com.saucedemo.page.CheckoutPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {
    private final TestContext tc;

    // POMs usados en el flujo de checkout
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;
    private final CheckoutOverviewPage overviewPage;
    private final CheckoutCompletePage completePage;

    public CheckoutSteps(TestContext tc) {
        this.tc = tc;
        // inyectamos la Page a cada Page Object
        this.cartPage = new CartPage(tc.page);
        this.checkoutPage = new CheckoutPage(tc.page);
        this.overviewPage = new CheckoutOverviewPage(tc.page);
        this.completePage = new CheckoutCompletePage(tc.page);
    }

    /** Paso: When hace click en "Checkout" */
    public void clickCheckout() {
        cartPage.clickCheckout();
    }

    public void fillAndContinue(String first, String last, String zip) {
        checkoutPage.fillYourInformation(first, last, zip);
    }

    public void assertOverviewItems(int expected) {
        int count = overviewPage.itemsCount();
        assertEquals(expected, count, "Ítems en overview no coinciden");
    }

    public void finish() {
        overviewPage.finish();
    }

    public void assertCompleteMessage(String expected) {
        String actual = completePage.header();
        assertEquals(expected.trim(), actual.trim());
    }

    public void assertFormError(String expected) {
        String actual = checkoutPage.errorMessage();
        assertEquals(expected.trim(), actual.trim());
    }
}
