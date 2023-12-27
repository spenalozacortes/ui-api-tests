package tests;

import api.PhotoSteps;
import api.PostSteps;
import config.CredentialsConfig;
import config.EnvironmentConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyProfilePage;
import pages.NewsPage;
import pages.PasswordPage;
import utils.RandomUtils;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTests extends BaseTest {

    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private static final int POST_LENGTH = 100;
    private final PostSteps postSteps = new PostSteps();
    private final PhotoSteps photoSteps = new PhotoSteps();
    private HomePage homePage;
    private PasswordPage passwordPage;
    private NewsPage newsPage;
    private MyProfilePage myProfilePage;

    @Test
    public void vkTest() {
        getBrowser().goTo(EnvironmentConfig.getUrl());

        homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Welcome page is not displayed");
        homePage.setUser(USER);
        homePage.clickSignIn();

        passwordPage = new PasswordPage();
        Assert.assertTrue(passwordPage.state().waitForDisplayed(), "Password page is not displayed");
        passwordPage.setPassword(PASSWORD);
        passwordPage.clickContinue();

        newsPage = new NewsPage();
        Assert.assertTrue(newsPage.state().waitForDisplayed(), "News page is not displayed");
        newsPage.leftMenuForm().clickMyProfile();

        myProfilePage = new MyProfilePage();
        Assert.assertTrue(myProfilePage.state().waitForDisplayed(), "My Profile page is not displayed");
        String postMessage = RandomUtils.generateRandomString(POST_LENGTH);
        postSteps.createPost(postMessage);
        String editedMessage = RandomUtils.generateRandomString(POST_LENGTH);
        postSteps.editPost(12, editedMessage);

        photoSteps.saveFile("src/test/resources/eviljenkins.PNG");
    }
}
