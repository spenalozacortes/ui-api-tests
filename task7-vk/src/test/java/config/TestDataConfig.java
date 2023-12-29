package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataConfig {

    private static final ISettingsFile DATA = new JsonSettingsFile("testData.json");

    public static String getImagePath() {
        return DATA.getValue("/imagePath").toString();
    }
}
