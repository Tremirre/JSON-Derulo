package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonTransformer {
    private JsonNode node;

    public JsonTransformer(String stringJson) throws JsonProcessingException {
        JsonReader transformer = new JsonReader(stringJson);
        this.node = transformer.getJsonNode();
    }

    public String minifyJson(){
        return "";
    }

    public String unminifyJson(){
        return "";
    }

    public String removeKeys(String[] keys){
        return "";
    }

    public String retainKeys(String[] keys){
        return "";
    }
}
