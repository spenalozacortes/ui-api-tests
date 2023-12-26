package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import org.testng.annotations.Test;
import pages.HomePage;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTest {

    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private HomePage homePage;

    @Test
    public void vkTest() {
        getBrowser().goTo(EnvironmentConfig.getUrl());

        homePage = new HomePage();
        homePage.setUser(USER);
        homePage.clickSignIn();
    }
}
