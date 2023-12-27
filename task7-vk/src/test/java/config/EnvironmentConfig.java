package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentConfig {

    private static final ISettingsFile ENVIRONMENT = new JsonSettingsFile("environment.json");

    public static String getUrl() {
        return ENVIRONMENT.getValue("/url").toString();
    }

    public static String getBaseUri() {
        return ENVIRONMENT.getValue("/baseUri").toString();
    }
}
