package cz.ivosahlik.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimplePlaywrightTest {

    @Test
    void shouldShowThePageTitle() {
        Playwright playwright = Playwright.create();

//        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
//                .setHeadless(false)
//                .setSlowMo(200);
//
//        Browser browser = playwright
//                .chromium()
//                .launch(launchOptions);

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(200));

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1440, 900)
        );

        Page page = context.newPage();

        page.navigate("https://practicesoftwaretesting.com");

        page.locator("[placeholder=Search]").fill("Bolt");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0);

        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));

        // získej texty
        List<String> results = page.locator(".card").allInnerTexts();

        Assertions.assertFalse(results.isEmpty());

        results.forEach(System.out::println);

        browser.close();
        playwright.close();
    }
}
