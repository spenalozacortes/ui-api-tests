package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private final ITextBox userTb = getElementFactory().getTextBox(By.id("index_email"), "User field");
    private final IButton signInBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Sign in button");

    public HomePage() {
        super(By.className("VkIdForm"), "Home Page");
    }

    public void setUser(String user) {
        userTb.type(user);
    }

    public void clickSignIn() {
        signInBtn.click();
    }
}
