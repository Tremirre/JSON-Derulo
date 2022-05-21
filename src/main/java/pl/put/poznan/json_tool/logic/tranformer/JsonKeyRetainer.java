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
    public JsonKeyRetainer(String jsonString, String[] keys) throws JsonProcessingException {
        super(jsonString);
        this.keys= keys;
    }

    @Override
    protected ObjectNode rawTransform() {
        return super.rawTransform().retain(Arrays.asList(keys));
    }

    /**
     * Retain only specified keys
     * @return json with selected keys
     */
    @Override
    public String transform() {
        return rawTransform().toPrettyString();
    }
}
