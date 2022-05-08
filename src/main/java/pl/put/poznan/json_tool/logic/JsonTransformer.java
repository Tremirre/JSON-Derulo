package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonTransformer {

    private ObjectMapper mapper;
    private JsonNode node;

    public JsonTransformer(String source) throws JsonProcessingException {
        this.mapper = getObjectMapper();
        this.node = mapper.readTree(source);
    }

    private static ObjectMapper getObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public String minify() throws JsonProcessingException {
        return mapper.writeValueAsString(node);
    }

    public String unminify() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    public String removeKeys(String[] keys){
        return "";
    }

    public String retainKeys(String[] keys){
        return "";
    }
}
