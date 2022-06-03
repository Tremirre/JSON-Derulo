package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUnminifierTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    final private String simpleJson = "{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":0.55}";
    final private String expectedJson = "{\r\n  \"id\" : \"0001\",\r\n" +
            "  \"type\" : \"donut\",\r\n" +
            "  \"name\" : \"Cake\",\r\n" +
            "  \"ppu\" : 0.55\r\n}";
    private ObjectNode jsonNode;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.jsonNode = (ObjectNode)objectMapper.readTree(simpleJson);
    }

    @Test
    void testTransform() throws JsonProcessingException {
        String res = new JsonUnminifier(jsonNode).transform();
        assertEquals(expectedJson, res);
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        String res = new JsonUnminifier(new JsonKeyRemover(this.jsonNode, new String[]{"-"})).transform();
        assertEquals(expectedJson, res);
    }
}