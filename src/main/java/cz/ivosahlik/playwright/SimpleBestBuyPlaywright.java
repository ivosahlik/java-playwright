package cz.ivosahlik.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class SimpleBestBuyPlaywright {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser
                .newContext(new Browser.NewContextOptions().setPermissions(List.of("geolocation")))
                .newPage();

        page.navigate("https://www.bestbuy.com/?intl=nosplash");

        Locator menuItems = page.locator("ul.bottom-nav-left li a");
        log.info("{}", menuItems.allTextContents());

        IntStream.range(0, menuItems.count()).forEach(i -> log.info("{}", menuItems.nth(i).textContent()));

        page.locator("ul.bottom-nav-left li a", new Page.LocatorOptions().setHasText("Deal of the Day")).click();

        browser.close();
        playwright.close();

        log.info("finish");
    }
}
