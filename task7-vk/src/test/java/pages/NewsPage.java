package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.forms.LeftMenuForm;

public class NewsPage extends Form {

    private final LeftMenuForm leftMenuForm = new LeftMenuForm();

    public NewsPage() {
        super(By.id("main_feed"), "News page");
    }

    public LeftMenuForm leftMenuForm() {
        return this.leftMenuForm;
    }
}
