package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonMapperUtils {

    public static <T> T deserialize(String json, String path, Class<T> targetClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            JsonNode subNode = rootNode.path(path);
            if (subNode.isArray()) {
                JsonNode firstElement = subNode.get(0);
                return mapper.treeToValue(firstElement, targetClass);
            }
            return mapper.treeToValue(subNode, targetClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
