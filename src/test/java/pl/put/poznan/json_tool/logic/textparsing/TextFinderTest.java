package pl.put.poznan.json_tool.logic.textparsing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextFinderTest {
    String simpleJson = "{ \"id\": \"0001\",\n" +
            "\t\"name\": \"Cake\",\n" +
            "\t\"type\": \"donut\",\n" +
            "\t\"ppu\": 0.55 }";

    String text = "Cake";

    @Test
    void differentLines(){
        TextFinder finder = new TextFinder(simpleJson, text);
        assertEquals(finder.findLines(), Arrays.asList(2));
    }

    @Test
    void emptyKeyTest(){
        TextFinder finder = new TextFinder(simpleJson, "");
        assertEquals(finder.findLines(), Arrays.asList(1,2,3,4));
    }

    @Test
    void wrongKeyTest(){
        TextFinder finder = new TextFinder(simpleJson, "test");
        assertEquals(finder.findLines(), Arrays.asList());
    }
}
