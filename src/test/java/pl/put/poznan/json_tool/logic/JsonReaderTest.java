package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    String simpleJson = "{ \"id\": \"0001\",\n" +
                        "\t\"type\": \"donut\",\n" +
                        "\t\"name\": \"Cake\",\n" +
                        "\t\"ppu\": 0.55, }";
    @Test
    void getJsonNode() throws JsonProcessingException {
        JsonReader transformer = new JsonReader(simpleJson);
        JsonNode node = transformer.getJsonNode();
        assertEquals(node.get("type").asText(), "donut");
    }
}