package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    private final IButton likeBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'PostButtonReactions')]"), "Like button");
    private final ILabel postText = getElementFactory().getLabel(By.className("wall_post_text"), "Post text");
    private final ILabel authorLabel = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'wall_post_cont')]"), "Author label");
    private final ILink photoLink = getElementFactory().getLink(By.xpath("//a[contains(@class,'image_cover')]"), "Image link");
    private final ILink nextCommentLink = getElementFactory().getLink(By.xpath("//a[contains(@class, 'replies_next')]"), "Show next comment link");
    private final ILink replyAuthorLink = getElementFactory().getLink(By.xpath("//*[@class='reply_author']//a[contains(@class, 'author')]"), "Reply author link");

    public MyProfilePage() {
        super(By.className("ProfileHeaderButton"), "My Profile page");
    }

    public void clickLikeBtn() {
        likeBtn.click();
    }

    public String getPostText() {
        return postText.getText();
    }

    public String getAuthor() {
        return authorLabel.getAttribute("id");
    }

    public String getPhoto() {
        return photoLink.getHref();
    }

    public void clickNextCommentLink() {
        nextCommentLink.click();
    }

    public String getReplyAuthor() {
        return replyAuthorLink.getHref();
    }

    public boolean isPostDisplayed() {
        return postText.state().isDisplayed();
    }
}
