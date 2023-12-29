package tests;

import api.PhotoSteps;
import api.WallSteps;
import config.CredentialsConfig;
import config.EnvironmentConfig;
import config.TestDataConfig;
import constants.Constants;
import models.LikesResponse;
import models.PhotoResponse;
import models.PostResponse;
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
    private static final String IMAGE_PATH = TestDataConfig.getImagePath();
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
        PostResponse post = wallSteps.createPost(postMessage);
        int postId = post.getResponse().getPost_id();
        int ownerId = TestDataConfig.getOwnerId();
        Assert.assertEquals(myProfilePage.getPostText(), postMessage, "Post text is not as expected");
        Assert.assertTrue(myProfilePage.getAuthor().contains(String.valueOf(ownerId)), "Post author is incorrect");

        String editedMessage = RandomUtils.generateRandomString(Constants.POST_LENGTH);
        PhotoResponse savePhoto = photoSteps.saveFile(IMAGE_PATH);
        int photoId = savePhoto.getResponse().get(0).getId();
        String photo = String.format("photo%d_%d", ownerId, photoId);
        wallSteps.editPost(postId, editedMessage, photo);
        Assert.assertEquals(myProfilePage.getPostText(), editedMessage, "Post text is not updated");
        Assert.assertTrue(myProfilePage.getPhoto().contains(photo), "Photos are not the same");

        String comment = RandomUtils.generateRandomString(Constants.COMMENT_LENGTH);
        wallSteps.addCommentToPost(postId, comment);
        myProfilePage.clickNextCommentLink();
        Assert.assertTrue(myProfilePage.getReplyAuthor().contains(String.valueOf(ownerId)), "Comment is from incorrect user");

        myProfilePage.clickLikeBtn();
        LikesResponse likes = wallSteps.getLikesFromPost(postId);
        Assert.assertTrue(likes.getResponse().getItems().contains(ownerId), "Like is not from expected user");

        wallSteps.deletePost(postId);
        Assert.assertFalse(myProfilePage.isPostDisplayed(), "Post is not deleted");
    }
}
