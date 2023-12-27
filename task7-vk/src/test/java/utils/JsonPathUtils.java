package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonPathUtils {

    public static String getValueFromResponseByKey(Response response, String key) {
        JsonPath js = new JsonPath(response.asString());
        return js.getString(key);
    }
}
