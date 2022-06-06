package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

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
        String res = new JsonUnminifier(this.objectMapper, jsonNode).transform();
        assertEquals(expectedJson, res);
    }

    @Test
    void testTransformWithPrevious() throws JsonProcessingException{
        String res = new JsonUnminifier(new JsonKeyRemover(this.objectMapper, this.jsonNode, new String[]{"-"})).transform();
        assertEquals(expectedJson, res);
    }

    @Test
    void testObjectConstruct() throws JsonProcessingException {
        var mockMapper = mock(ObjectMapper.class);
        var mockWriter = mock(ObjectWriter.class);
        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);
        var transformer = new JsonUnminifier(mockMapper, jsonNode);
        String res = transformer.transform();
        verify(mockMapper, times(1)).writerWithDefaultPrettyPrinter();
        verify(mockWriter, times(1)).writeValueAsString(argThat(obj -> true));
        verifyNoMoreInteractions(mockMapper);
        verifyNoMoreInteractions(mockWriter);
    }
}