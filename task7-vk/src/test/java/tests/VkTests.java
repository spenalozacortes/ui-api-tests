package tests;

import api.PostSteps;
import config.CredentialsConfig;
import config.EnvironmentConfig;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewsPage;
import pages.PasswordPage;
import utils.RandomUtils;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTests extends BaseTest {

    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private static final int POST_LENGTH = 100;
    private final PostSteps postSteps = new PostSteps();
    private HomePage homePage;
    private PasswordPage passwordPage;
    private NewsPage newsPage;

    @Test
    public void vkTest() {
        getBrowser().goTo(EnvironmentConfig.getUrl());

        homePage = new HomePage();
        homePage.setUser(USER);
        homePage.clickSignIn();

        passwordPage = new PasswordPage();
        passwordPage.setPassword(PASSWORD);
        passwordPage.clickContinue();

        newsPage = new NewsPage();
        newsPage.leftMenuForm().clickMyProfile();

        String postMessage = RandomUtils.generateRandomString(POST_LENGTH);
        postSteps.createPost(postMessage);
        String editedMessage = RandomUtils.generateRandomString(POST_LENGTH);
        postSteps.editPost(10, editedMessage);
    }
}
