package com.saucedemo.stepsdefinitions;

import com.microsoft.playwright.Page;
import com.saucedemo.core.TestContext;
import io.cucumber.java.*;

public class Hooks {

    private final TestContext tc;

    public Hooks(TestContext tc) {
        this.tc = tc;
    }

    @Before(order = 0)
    public void beforeScenario(Scenario s) {
        // Arranca Playwright (crea playwright, browser, context, page)
        tc.start();

        // Si quieres empezar siempre en login, descomenta:
        // String base = System.getProperty("BASE_URL", "https://www.saucedemo.com/");
        // tc.page.navigate(base);
    }

    @After(order = 0) // primero: evidencia si falla
    public void afterScenario(Scenario s) {
        if (s.isFailed()) {
            try {
                byte[] png = tc.page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                s.attach(png, "image/png", "failure");
            } catch (Exception ignored) {}
        }
    }

    @After(order = 100) // último: cerrar todo
    public void closeAll() {
        tc.close();
    }
}
