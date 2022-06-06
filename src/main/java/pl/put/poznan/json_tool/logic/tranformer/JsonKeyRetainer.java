package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;

/**
 * Get a JSON-formatted structure with only certain keys to simplify the structure.
 */
public class JsonKeyRetainer extends BaseJsonTransformer{
    protected String[] keys;

    public JsonKeyRetainer(BaseJsonTransformer nextTransform, String[] keys) {
        super(nextTransform);
        this.keys = keys;
    }
    public JsonKeyRetainer(ObjectMapper mapper, ObjectNode json, String[] keys){
        super(mapper, json);
        this.keys= keys;
    }

    /**
     * Retains keys provided in the constructor.
     * @return json object node with provided keys only
     */
    @Override
    public ObjectNode rawTransform() {
        return super.rawTransform().retain(Arrays.asList(keys));
    }

    /**
     * Retains keys provided in the constructor.
     * @return json string with provided keys only
     */
    @Override
    public String transform() {
        return rawTransform().toPrettyString();
    }
}
