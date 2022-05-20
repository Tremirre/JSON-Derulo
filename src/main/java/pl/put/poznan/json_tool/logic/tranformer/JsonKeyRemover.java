package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;

public class JsonKeyRemover extends BaseJsonTransformer{
    protected String[] keys;

    public JsonKeyRemover(BaseJsonTransformer nextTransform, String[] keys) {
        super(nextTransform);
        this.keys = keys;
    }
    public JsonKeyRemover(String jsonString, String[] keys) {
        super(jsonString);
        this.keys= keys;
    }

    @Override
    public String transform() throws JsonProcessingException {
        if(this.previousTransformer != null) {
            this.jsonString = this.previousTransformer.transform();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = (ObjectNode)mapper.readTree(this.jsonString);
        return objectNode.remove(Arrays.asList(keys)).toPrettyString();
    }
}
