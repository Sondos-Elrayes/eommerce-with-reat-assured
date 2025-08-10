package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataLoader {
    private static JsonNode rootNode;
    static {
       try {
           InputStream is = JsonDataLoader.class.getClassLoader().getResourceAsStream("ecom-data.json");
           ObjectMapper mapper = new ObjectMapper();
           rootNode = mapper.readTree(is);
       } catch (Exception e) {
           throw new RuntimeException("Failed to load data ",e);
       }
   }
    public static String getValue(String path) {
        String[] keys = path.split("\\.");
        JsonNode currentNode = rootNode;

        for (String key : keys) {
            currentNode = currentNode.path(key);
        }

        return currentNode.asText();
    }



}
