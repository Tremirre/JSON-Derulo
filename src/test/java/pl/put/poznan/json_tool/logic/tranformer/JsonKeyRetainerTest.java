package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonKeyRetainerTest {
    private ObjectNode json;
    private JsonKeyRetainer jsonKeyRetainer;
    private ObjectMapper objectMapper;
    final private String[] keys = new String[]{"id", "name"};
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
        this.jsonKeyRetainer = new JsonKeyRetainer(this.objectMapper, json, keys);
    }

    @Test
    void testTransform() throws JsonProcessingException {
        String res = jsonKeyRetainer.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }

    @Test
    void testRawTransform() {
        ObjectNode res = jsonKeyRetainer.rawTransform();
        assertEquals(expectedNode, res);
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException {
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), keys);
        String res = jsonKeyRetainer.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }

    @Test
    void testNullKey() throws JsonProcessingException{
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), new String[]{null});
        String res = jsonKeyRetainer.transform();
        assertEquals("{}", String.valueOf(objectMapper.readTree(res)));
    }

    @Test
    void testWrongKey() throws JsonProcessingException{
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), new String[]{"aaa"});
        String res = jsonKeyRetainer.transform();
        assertEquals("{}", String.valueOf(objectMapper.readTree(res)));
    }

    @Test
    void testPartialWrongKey() throws JsonProcessingException{
        this.jsonKeyRetainer = new JsonKeyRetainer(new JsonMinifier(new JsonUnminifier(this.objectMapper, json)), new String[]{"aaa", "id", "name"});
        String res = jsonKeyRetainer.transform();
        assertEquals(expectedNode, objectMapper.readTree(res));
    }

    @Test
    void testObjectConstruct() throws JsonProcessingException {
        var mockMapper = mock(ObjectMapper.class);
        var transformer = new JsonKeyRetainer(mockMapper, json, keys);
        String res = transformer.transform();
        verify(mockMapper, times(0)).writeValueAsString(null);
        verifyNoMoreInteractions(mockMapper);
    }
}