package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;


public class JsonTransformer {

    private ObjectMapper mapper;
    private ObjectNode objectNode;

    public JsonTransformer() {
        this.mapper = new ObjectMapper();
    }

    public void setNode(String source) throws JsonProcessingException {
        this.objectNode = (ObjectNode)mapper.readTree(source);
    }

    public String minify() throws JsonProcessingException {
        return mapper.writeValueAsString(objectNode);
    }

    public String unminify() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
    }

    public String removeKeys(String[] keys) {
        return objectNode.remove(Arrays.asList(keys)).toPrettyString();
    }

    public String retainKeys(String[] keys){
        return objectNode.retain(Arrays.asList(keys)).toPrettyString();
    }
}