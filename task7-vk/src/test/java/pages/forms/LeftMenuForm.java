package pages.forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LeftMenuForm extends Form {

    private final ILink myProfileLink = getElementFactory().getLink(By.id("l_pr"), "My Profile link");

    public LeftMenuForm() {
        super(By.id("side_bar_inner"), "Left menu form");
    }

    public void clickMyProfile() {
        myProfileLink.click();
    }
}
