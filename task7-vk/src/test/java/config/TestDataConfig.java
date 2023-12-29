package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestDataConfig {

    private static final ISettingsFile DATA = new JsonSettingsFile("testData.json");

    public static int getOwnerId() {
        return (int) DATA.getValue("/owner_id");
    }

    public static String getVersion() {
        return DATA.getValue("/version").toString();
    }

    public static String getImagePath() {
        return DATA.getValue("/imagePath").toString();
    }
}
