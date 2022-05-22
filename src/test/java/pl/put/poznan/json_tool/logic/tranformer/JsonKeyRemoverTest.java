package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonKeyRemoverTest {
    private ObjectNode json;
    private JsonKeyRemover jsonKeyRemover;
    private ObjectMapper objectMapper;
    final private String[] keys = new String[]{"type", "ppu"};
    final private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";
    final private String expectedJson = "{\"id\": \"0001\", \n\t\"name\": \"Cake\" }";
    private ObjectNode expectedNode;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.expectedNode = (ObjectNode)this.objectMapper.readTree(expectedJson);
        this.json = (ObjectNode)this.objectMapper.readTree(simpleJson);
        this.jsonKeyRemover = new JsonKeyRemover(json, keys);
    }

    @Test
    void testTransform() throws JsonProcessingException {
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }

    @Test
    void testRawTransform() {
        ObjectNode res = jsonKeyRemover.rawTransform();
        assertEquals(expectedNode, res);
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException {
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(json)), keys);
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }
}