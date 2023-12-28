package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {

    public static <T> T getValueFromResponseByKey(Response response, String key) {
        JsonPath js = new JsonPath(response.asString());
        return js.get(key);
    }
}
