package cz.ivosahlik.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SimplePlaywright {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();

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
        log.info("Matching search results: {}", matchingSearchResults);
        assert matchingSearchResults > 0 : "Expected search results to be greater than 0";

        String title = page.title();
        log.info("Page title: {}", title);
        assert title.contains("Practice Software Testing") : "Expected title to contain 'Practice Software Testing'";

        // získej texty
        List<String> results = page.locator(".card").allInnerTexts();

        assert !results.isEmpty() : "Expected results to not be empty";

        log.info("\nSearch results:");
        results.forEach(System.out::println);

        browser.close();
        playwright.close();

        log.info("\nTest completed successfully!");
    }
}
