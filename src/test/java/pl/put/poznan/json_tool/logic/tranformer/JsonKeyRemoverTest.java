package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonKeyRemoverTest {
    private ObjectNode json;
    private ObjectNode nestedJson;
    private JsonKeyRemover jsonKeyRemover;
    private ObjectMapper objectMapper;
    final private String[] keys = new String[]{"type", "ppu"};
    final private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";
    final private String expectedJson = "{\"id\": \"0001\", \n\t\"name\": \"Cake\" }";

    final private String nestedJsons = "{ \"isbn\": \"123-456-222\",\n" +
            "\t\"author\": \n" +
            "\t\t{ \"lastname\": \"Doe\", \n" +
            "\t\t\"firstname\": \"Jane\" }, \n" +
            "\t\"title\": \"The Ultimate Database Study Guide\", \n" +
            "\t\"category\": [\"Non-Fiction\", \"Technology\"]}";

    final private String expectedNestedJson = "{ \"isbn\": \"123-456-222\",\n" +
            "\t\"author\": \n" +
            "\t\t{ \"lastname\": \"Doe\", \n" +
            "\t\t\"firstname\": \"Jane\" }, \n" +
            "\t\"title\": \"The Ultimate Database Study Guide\"}";
    private ObjectNode expectedNode;
    private ObjectNode expectedNestedNode;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.expectedNode = (ObjectNode)this.objectMapper.readTree(expectedJson);
        this.expectedNestedNode = (ObjectNode)this.objectMapper.readTree(expectedNestedJson);
        this.json = (ObjectNode)this.objectMapper.readTree(simpleJson);
        this.nestedJson = (ObjectNode)this.objectMapper.readTree(nestedJsons);
        this.jsonKeyRemover = new JsonKeyRemover(this.objectMapper, json, keys);
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
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), keys);
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }

    @Test
    void testNestedRemover() throws JsonProcessingException {
        this.jsonKeyRemover = new JsonKeyRemover(this.objectMapper, nestedJson, new String[]{"category"});
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNestedNode, objectMapper.readTree(res));
    }

    @Test
    void testNestedTransformWithPrevious() throws JsonProcessingException {
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(this.objectMapper, nestedJson)), new String[]{"category"});
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNestedNode, objectMapper.readTree(res));
    }

    @Test
    void testNullKey() throws JsonProcessingException{
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), new String[]{null});
        String res = jsonKeyRemover.transform();
        assertEquals(objectMapper.readTree(this.simpleJson), objectMapper.readTree(res));
    }

    @Test
    void testPartialWrongKey() throws JsonProcessingException {
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), new String[]{"type", "ppu", "aa", "b"});
        String res = jsonKeyRemover.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }
}