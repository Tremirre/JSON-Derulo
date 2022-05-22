package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.utils.JsonValidChecker;

import static org.junit.jupiter.api.Assertions.*;

class JsonKeyRetainerTest {
    private ObjectNode json;
    private JsonKeyRetainer jsonKeyRetainer;
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
        this.jsonKeyRetainer = new JsonKeyRetainer(json, keys);
    }

    @Test
    void testTransform() {
        String res = jsonKeyRetainer.transform();
        assertTrue(ju.isValidJson(res));
    }
    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(json), keys);
        String res = jsonKeyRetainer.transform();
        assertTrue(ju.isValidJson(res));
    }

}