package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {

    private final ITextBox passwordTb = getElementFactory().getTextBox(By.xpath("//input[@type='password']"), "Password field");
    private final IButton continueBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Continue button");

    public PasswordPage() {
        super(By.xpath("//*[contains(@class, 'EnterPassword')]"), "Password page");
    }

    public void setPassword(String password) {
        passwordTb.type(password);
    }

    public void clickContinue() {
        continueBtn.click();
    }
}
