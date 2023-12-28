package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    private final IButton likeBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'PostButtonReactions')]"), "Like button");
    private final ILabel postText = getElementFactory().getLabel(By.className("wall_post_text"), "Post text");
    private final ILink authorLink = getElementFactory().getLink(By.className("PostHeaderTitle__authorLink"), "Author link");

    public MyProfilePage() {
        super(By.className("ProfileHeaderButton"), "My Profile page");
    }

    public void clickLikeBtn() {
        likeBtn.click();
    }

    public String getPostText() {
        return postText.getText();
    }

    public String getAuthorId() {
        return authorLink.getAttribute("data-from-id");
    }
}
