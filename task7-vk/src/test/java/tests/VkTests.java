package tests;

import api.PhotoSteps;
import api.UserSteps;
import api.WallSteps;
import config.CredentialsConfig;
import config.EnvironmentConfig;
import config.TestDataConfig;
import constants.Keys;
import constants.Constants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyProfilePage;
import pages.NewsPage;
import pages.PasswordPage;
import utils.ResponseUtils;
import utils.RandomUtils;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class VkTests extends BaseTest {

    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private static final String IMAGE_PATH = TestDataConfig.getImagePath();
    private final UserSteps userSteps = new UserSteps();
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

        Response user = userSteps.getUser();
        String ownerId = ResponseUtils.getValueFromResponseByKey(user, Keys.USER_ID).toString();
        String postMessage = RandomUtils.generateRandomString(Constants.POST_LENGTH);
        Response createPost = wallSteps.createPost(postMessage);
        int postId = ResponseUtils.getValueFromResponseByKey(createPost, Keys.POST_ID);
        Assert.assertEquals(myProfilePage.getPostText(), postMessage, "Post text is not as expected");
        Assert.assertTrue(myProfilePage.getAuthor().contains(ownerId), "Post author is incorrect");

        String editedMessage = RandomUtils.generateRandomString(Constants.POST_LENGTH);
        Response savePhoto = photoSteps.saveFile(IMAGE_PATH);
        int photoId = ResponseUtils.getValueFromResponseByKey(savePhoto, Keys.PHOTO_ID);
        String photo = String.format("photo%s_%d", ownerId, photoId);
        wallSteps.editPost(postId, editedMessage, photo);
        Assert.assertEquals(myProfilePage.getPostText(), editedMessage, "Post text wasn't updated");
        Assert.assertTrue(myProfilePage.getPhoto().contains(photo), "Photos are not the same");

        String comment = RandomUtils.generateRandomString(Constants.COMMENT_LENGTH);
        wallSteps.addCommentToPost(postId, comment);
        Assert.assertTrue(myProfilePage.getReplyAuthor().contains(ownerId), "Comment from incorrect user");

        myProfilePage.clickLikeBtn();
        Response likes = wallSteps.getLikesFromPost(postId);
        String likeUserId = ResponseUtils.getValueFromResponseByKey(likes, Keys.LIKE_USER_ID).toString();
        Assert.assertEquals(likeUserId, ownerId, "Like from incorrect user");

//        wallSteps.deletePost(postId);
    }
}
