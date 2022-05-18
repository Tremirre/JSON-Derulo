package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json_tool.logic.tranformer.JsonsComparator;

class JsonsComparatorTest {

    String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"ppu\": 0.55 }";

    String simpleJson2 = "{ \"id\": \"0001\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"type\":       \"donut\",\n" +
            "\t\"ppu\": 0.555 }";

    @Test
    void differentLines() throws JsonProcessingException {
        JsonsComparator comparator = new JsonsComparator();
        System.out.println(comparator.differentLines(simpleJson, simpleJson2));
//        assertEquals...
    }
}