package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    public MyProfilePage() {
        super(By.className("ProfileWrapper"), "My Profile page");
    }
}
