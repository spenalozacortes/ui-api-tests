package tests;

import config.EnvironmentConfig;
import org.testng.annotations.Test;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTest {

    @Test
    public void vkTest() {
        getBrowser().goTo(EnvironmentConfig.getUrl());
    }
}
