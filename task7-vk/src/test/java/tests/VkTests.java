package tests;

import api.PhotoSteps;
import api.WallSteps;
import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Keys;
import constants.Constants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyProfilePage;
import pages.NewsPage;
import pages.PasswordPage;
import utils.JsonPathUtils;
import utils.RandomUtils;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTests extends BaseTest {

    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private final WallSteps wallSteps = new WallSteps();
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
        String postMessage = RandomUtils.generateRandomString(Constants.POST_LENGTH);
        wallSteps.createPost(postMessage);

        int postId = 12;
        int ownerId = 841084343;
        String editedMessage = RandomUtils.generateRandomString(Constants.POST_LENGTH);
        Response savePhoto = photoSteps.saveFile(Constants.IMAGE_PATH);
        int photoId = JsonPathUtils.getValueFromResponseByKey(savePhoto, Keys.PHOTO_ID);
        String attachment = String.format("photo%d_%d", ownerId, photoId);
        wallSteps.editPost(postId, editedMessage, attachment);

        String comment = RandomUtils.generateRandomString(Constants.COMMENT_LENGTH);
        wallSteps.addCommentToPost(postId, comment);

        myProfilePage.clickLikeBtn();

        wallSteps.getLikesFromPost(postId);

        wallSteps.deletePost(postId);
    }
}
