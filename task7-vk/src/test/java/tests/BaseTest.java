package tests;

import org.testng.annotations.AfterMethod;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.isBrowserStarted;

public abstract class BaseTest {

    @AfterMethod
    public void tearDown() {
        if (isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
