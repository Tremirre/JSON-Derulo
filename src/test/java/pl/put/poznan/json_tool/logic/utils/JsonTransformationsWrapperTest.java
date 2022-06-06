package pl.put.poznan.json_tool.logic.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.tranformer.BaseJsonTransformer;

public class JsonTransformationsWrapperTest {

    private JsonTransformationsWrapper wrapper;

    @BeforeEach
    void setUp() {
        wrapper = new JsonTransformationsWrapper(new String[]{"minify", "unminify"}, new String[]{"c", "d"});
    }

    @Test
    void testAddAction() throws JsonProcessingException {
        var mockTransformer = mock(BaseJsonTransformer.class);
        var mapper = new ObjectMapper();
        var node = (ObjectNode)mapper.readTree("{\"test\":\"nope\"}");
        when(mockTransformer.rawTransform()).thenReturn(node);
        when(mockTransformer.getMapper()).thenReturn(mapper);
        var nextTransformer = wrapper.addAction(mockTransformer, "minify");
        nextTransformer.transform();
        verify(mockTransformer, times(1)).rawTransform();
        verify(mockTransformer, times(1)).getMapper();
    }

    @Test
    void testGetTransformer() throws JsonProcessingException {
        var mockNode = mock(ObjectNode.class);
        var nextTransformer = wrapper.getTransformer(mockNode);
        nextTransformer.transform();
        verify(mockNode, times(1)).getClass();
    }
}
