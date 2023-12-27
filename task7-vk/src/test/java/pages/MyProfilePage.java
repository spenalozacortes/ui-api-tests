package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    private final IButton likeBtn = getElementFactory().getButton(By.xpath("//div[contains(@class,'PostButtonReactions__icon')]"), "Like button");

    public MyProfilePage() {
        super(By.className("ProfileWrapper"), "My Profile page");
    }

    public void clickLikeBtn() {
        likeBtn.click();
    }
}
