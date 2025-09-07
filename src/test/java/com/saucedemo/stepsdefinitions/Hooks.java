package com.saucedemo.stepsdefinitions;

import com.microsoft.playwright.Page;
import com.saucedemo.core.TestContext;
import io.cucumber.java.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    @AfterStep("@screenshot")
    public void attachScreenshotAfterStep(Scenario scenario) {
        attachScreenshot(scenario, "after-step");
    }

    private void attachScreenshot(Scenario scenario, String label) {
        Page page = (tc != null) ? tc.page : null;
        if (page == null) return;

        try {

            String safe = scenario.getName().replaceAll("\\W+", "_");
            Path out = Paths.get("target", "screenshots", safe + "_" + System.currentTimeMillis() + ".png");
            Files.createDirectories(out.getParent());
            page.screenshot(new Page.ScreenshotOptions().setPath(out).setFullPage(true));

            byte[] bytes = Files.readAllBytes(out);
            scenario.attach(bytes, "image/png", label + "_" + out.getFileName().toString());
        } catch (Exception e) {
            System.err.println("No se pudo adjuntar screenshot: " + e.getMessage());
        }
    }
}
