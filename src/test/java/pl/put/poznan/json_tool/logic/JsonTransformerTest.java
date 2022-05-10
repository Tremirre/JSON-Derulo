package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTransformerTest {

    private JsonTransformer transformer;
    private JsonUtils ju;
    private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.transformer = new JsonTransformer();
        transformer.setNode(simpleJson);
        this.ju = new JsonUtils();
    }

    @Test
    void minify() throws JsonProcessingException {
        String res = transformer.minify();
//        System.out.println("MINIFIED:");
//        System.out.println(res);
        assertTrue(ju.isValidJson(res) && !res.contains("\n") && !res.contains(" "));
    }

    @Test
    void unminify() throws JsonProcessingException {
        String res = transformer.unminify();
//        System.out.println("\nUNMINIFIED:");
//        System.out.println(res);
        assertTrue(ju.isValidJson(res));
    }

    @Test
    void removeKeys() throws JsonProcessingException {
//        System.out.println("\nWithout keys:");
//        System.out.println(transformer.removeKeys(new String[]{"type", "ppu"}));
        String res = transformer.removeKeys(new String[]{"type", "ppu"});
        assertTrue(ju.isValidJson(res));
    }

    @Test
    void retainKeys() throws JsonProcessingException {
//        System.out.println("\nWith keys:");
//        System.out.println(transformer.retainKeys(new String[]{"type", "ppu"}));
        String res = transformer.retainKeys(new String[]{"type", "ppu"});
        assertTrue(ju.isValidJson(res));
    }
}