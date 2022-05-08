package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTransformer {
    private static ObjectMapper objectMapper = getObjectMapper();
    private String source;

    public JsonTransformer(String s){
        this.source = s;
    }

    private static ObjectMapper getObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public JsonNode getJsonNode() throws JsonProcessingException {
        return objectMapper.readTree(this.source);
    }

}
