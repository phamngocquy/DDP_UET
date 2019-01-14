package core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.dom.Node;

public class JsonHelper {
    private ObjectMapper objectMapper;
    public static JsonHelper instance = null;

    public static JsonHelper getInstance() {
        if (instance == null) {
            instance = new JsonHelper();
        }
        return instance;
    }

    public JsonHelper() {
        objectMapper = new ObjectMapper();
    }

    public String getJson(Node o) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
