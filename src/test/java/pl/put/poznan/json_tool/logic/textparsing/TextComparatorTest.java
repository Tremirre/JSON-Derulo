package pl.put.poznan.json_tool.logic.textparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.textparsing.TextComparator;
import pl.put.poznan.json_tool.logic.tranformer.JsonUnminifier;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextComparatorTest {

    String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"ppu\": 0.55 }";

    String simpleJson2 = "{ \"id\": \"0001\"," +
            "\"name\": \"Cake\"," +
            "\"type\":       \"donut\"," +
            "\"ppu\": 0.555, "+
            "\"ppu2\": 0.555, "+
            "\"ppu3\": 0.55 }";

    @Test
    void differentLines() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json1  = (ObjectNode)mapper.readTree(simpleJson);
        ObjectNode json2  = (ObjectNode)mapper.readTree(simpleJson2);

        TextComparator comparator = new TextComparator((new JsonUnminifier(json1)).transform(),
                (new JsonUnminifier(json2)).transform());
        assertEquals(comparator.differentLines(), Arrays.asList(5, 6, 7, 8));
    }
}