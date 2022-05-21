package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.JsonUtils;

import static org.junit.jupiter.api.Assertions.*;

class JsonKeyRetainerTest {
    private JsonKeyRetainer jsonKeyRetainer;
    private JsonUtils ju;
    private String[] keys = new String[]{"type", "ppu"};
    private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.jsonKeyRetainer = new JsonKeyRetainer(simpleJson, keys);
        this.ju = new JsonUtils();
    }

    @Test
    void testTransform() {
        String res = jsonKeyRetainer.transform();
        assertTrue(ju.isValidJson(res));
    }
    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(simpleJson), keys);
        String res = jsonKeyRetainer.transform();
        assertTrue(ju.isValidJson(res));
    }

}