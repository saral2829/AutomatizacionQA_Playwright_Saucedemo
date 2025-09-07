package com.saucedemo.core;

import com.microsoft.playwright.*;

public class TestContext {
    public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;

    public final String baseUrl;
    public final boolean headless;
    public final double slowMo;

    public TestContext() {
        // Configurables vía -DBASE_URL, -Dheadless, -DslowMo
        String url = System.getProperty("BASE_URL");
        if(url == null || url.isBlank() || url.isEmpty()){
            url="https://www.saucedemo.com/";
        }
        // normaliza para que siempre termine en '/'
        this.baseUrl = url.endsWith("/") ? url : url + "/";
        this.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        this.slowMo = Double.parseDouble(System.getProperty("slowMo", "0"));
        // ¡OJO! No arrancamos Playwright aquí. Se hace en start().
    }

    /** Arranca Playwright y abre una Page lista para usar. Idempotente. */
    public void start() {
        if (playwright != null && page != null) return; // ya iniciado

        playwright = Playwright.create();

        BrowserType.LaunchOptions launch = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo);

        // Si quieres usar Chrome estable en local: -Dchannel=chrome
        String channel = System.getProperty("channel");
        if (channel != null && !channel.isBlank()) {
            launch.setChannel(channel);
        }

        browser = playwright.chromium().launch(launch);
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1366, 768));
        page = context.newPage();

        // Timeouts razonables
        page.setDefaultTimeout(10_000);
        page.setDefaultNavigationTimeout(15_000);
    }

    public void close() {
        try { if (context != null) context.close(); } catch (Exception ignored) {}
        try { if (browser != null) browser.close(); } catch (Exception ignored) {}
        try { if (playwright != null) playwright.close(); } catch (Exception ignored) {}

        context = null;
        browser = null;
        playwright = null;
        page = null;
    }
}
