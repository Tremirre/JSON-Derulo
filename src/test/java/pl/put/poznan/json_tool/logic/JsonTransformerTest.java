package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTransformerTest {

    String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    @Test
    void minify() throws JsonProcessingException {
        JsonTransformer transformer = new JsonTransformer(simpleJson);
        System.out.println("MINIFIED:");
        System.out.println(transformer.minify());
    }

    @Test
    void unminify() throws JsonProcessingException {
        JsonTransformer transformer = new JsonTransformer(simpleJson);
        System.out.println("\nUNMINIFIED:");
        System.out.println(transformer.unminify());
    }

    @Test
    void removeKeys() throws JsonProcessingException {
        JsonTransformer transformer = new JsonTransformer(simpleJson);
        System.out.println("\nWithout keys:");
        System.out.println(transformer.removeKeys(new String[]{"type", "ppu"}));
    }

    @Test
    void retainKeys() throws JsonProcessingException {
        JsonTransformer transformer = new JsonTransformer(simpleJson);
        System.out.println("\nWith keys:");
        System.out.println(transformer.retainKeys(new String[]{"type", "ppu"}));
    }
}