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

    private String[] keys = new String[]{"type", "ppu"};
    private String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    @BeforeEach
    void setUp() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.json = (ObjectNode)mapper.readTree(simpleJson);
        this.jsonKeyRemover = new JsonKeyRemover(json, keys);
    }

    @Test
    void testTransform() {
        String res = jsonKeyRemover.transform();
//      TODO assertions!
    }

    @Test
    void testTransformWithPrevious(){
        this.jsonKeyRemover = new JsonKeyRemover(new JsonMinifier(new JsonUnminifier(json)), keys);
        String res = jsonKeyRemover.transform();
    }

}