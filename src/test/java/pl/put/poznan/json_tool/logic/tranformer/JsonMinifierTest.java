package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonMinifierTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    final private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";
    final private String expectedJson = "{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":0.55}";
    private ObjectNode jsonNode;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.jsonNode = (ObjectNode)objectMapper.readTree(simpleJson);
    }

    @Test
    void testTransform() throws JsonProcessingException {
        String res = new JsonMinifier(this.objectMapper, jsonNode).transform();
        assertEquals(expectedJson, res);
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        String res = new JsonMinifier(new JsonKeyRemover(this.objectMapper, this.jsonNode, new String[]{"-"})).transform();
        assertEquals(expectedJson, res);
    }

    @Test
    void testObjectConstruct() throws JsonProcessingException {
        var mockMapper = mock(ObjectMapper.class);
        var transformer = new JsonMinifier(mockMapper, jsonNode);
        String res = transformer.transform();
        verify(mockMapper, times(1)).writeValueAsString(argThat(obj -> true));
        verifyNoMoreInteractions(mockMapper);
    }
}