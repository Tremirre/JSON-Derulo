package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;

/**
 * Get a JSON-formatted structure with only certain keys to simplify the structure
 */
public class JsonKeyRetainer extends BaseJsonTransformer{
    protected String[] keys;

    public JsonKeyRetainer(BaseJsonTransformer nextTransform, String[] keys) {
        super(nextTransform);
        this.keys = keys;
    }
    public JsonKeyRetainer(String jsonString, String[] keys) {
        super(jsonString);
        this.keys= keys;
    }

    /**
     * Retain only specified keys
     * @return json with selected keys
     * @throws JsonProcessingException
     */
    @Override
    public String transform() throws JsonProcessingException {
        if(this.previousTransformer != null) {
            this.jsonString = this.previousTransformer.transform();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = (ObjectNode)mapper.readTree(this.jsonString);
        return objectNode.retain(Arrays.asList(keys)).toPrettyString();
    }
}
