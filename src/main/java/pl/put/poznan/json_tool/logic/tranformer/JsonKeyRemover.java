package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;

/**
 * Get a JSON structure without specific keys to simplify the structure.
 */
public class JsonKeyRemover extends BaseJsonTransformer{
    protected String[] keys;

    public JsonKeyRemover(BaseJsonTransformer nextTransform, String[] keys) {
        super(nextTransform);
        this.keys = keys;
    }
    public JsonKeyRemover(ObjectNode json, String[] keys){
        super(json);
        this.keys= keys;
    }

    /**
     * Removes keys provided in the constructor.
     * @return json object node without provided keys
     */
    @Override
    public ObjectNode rawTransform() {
        return super.rawTransform().remove(Arrays.asList(keys));
    }

    /**
     * Removes keys provided in the constructor.
     * @return json string without provided keys
     */
    @Override
    public String transform() {
        return rawTransform().toPrettyString();
    }
}
