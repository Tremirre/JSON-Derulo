package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.utils.JsonValidChecker;

import static org.junit.jupiter.api.Assertions.*;

class JsonMinifierTest {
    private ObjectNode json;
    private JsonMinifier jsonMinifier;
    private JsonValidChecker ju;
    private String[] keys = new String[]{"type", "ppu"};
    private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    @BeforeEach
    void setUp() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.json  = (ObjectNode)mapper.readTree(simpleJson);

        this.jsonMinifier = new JsonMinifier(json);
        this.ju = new JsonValidChecker();
    }

    @Test
    void testTransform() throws JsonProcessingException {
        String res = jsonMinifier.transform();
        assertTrue(ju.isValidJson(res) && !res.contains("\n") && !res.contains(" "));
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        this.jsonMinifier = new JsonMinifier(new JsonKeyRetainer(json, keys));
        String res = jsonMinifier.transform();
        assertTrue(ju.isValidJson(res) && !res.contains("\n") && !res.contains(" "));
    }

}