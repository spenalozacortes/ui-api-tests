package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    private static final String POST_XPATH = "//div[contains(@id, 'post') and contains(@id, '_%d')]";
    private static final By POST_TEXT_BY = By.className("wall_post_text");
    private static final By AUTHOR_BY = By.xpath("//*[contains(@class, 'wall_post_cont')]");
    private static final By PHOTO_LINK_BY = By.xpath("//a[contains(@class,'image_cover')]");
    private static final By NEXT_COMMENT_LINK_BY = By.xpath("//a[contains(@class, 'replies_next')]");
    private static final By REPLY_AUTHOR_LINK_BY = By.xpath("//*[@class='reply_author']//a[contains(@class, 'author')]");
    private static final By LIKE_BTN_BY = By.xpath("//*[contains(@class,'PostButtonReactions')]");
    private static final String ID = "id";

    public MyProfilePage() {
        super(By.className("ProfileHeaderButton"), "My Profile page");
    }

    public String getPostText(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILabel postText = post.findChildElement(POST_TEXT_BY, ElementType.LABEL);
        return postText.getText();
    }

    public String getAuthor(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILabel authorLabel = post.findChildElement(AUTHOR_BY, ElementType.LABEL);
        return authorLabel.getAttribute(ID);
    }

    public String getPhoto(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILink photoLink = post.findChildElement(PHOTO_LINK_BY, ElementType.LINK);
        return photoLink.getHref();
    }

    public void clickNextCommentLink(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILink nextCommentLink = post.findChildElement(NEXT_COMMENT_LINK_BY, ElementType.LINK);
        nextCommentLink.click();
    }

    public String getReplyAuthor(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILink replyAuthorLink = post.findChildElement(REPLY_AUTHOR_LINK_BY, ElementType.LINK);
        return replyAuthorLink.getHref();
    }

    public void clickLikeBtn(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        IButton likeBtn = post.findChildElement(LIKE_BTN_BY, ElementType.BUTTON);
        likeBtn.click();
    }

    public boolean isPostDisplayed(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(POST_XPATH, postId)), "Post");
        ILabel postText = post.findChildElement(POST_TEXT_BY, ElementType.LABEL);
        return postText.state().isDisplayed();
    }
}
