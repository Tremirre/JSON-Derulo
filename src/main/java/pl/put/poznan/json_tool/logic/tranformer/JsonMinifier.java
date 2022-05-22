package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Get a minified JSON structure from JSON full format to reduce data size
 */
public class JsonMinifier extends BaseJsonTransformer {
    public JsonMinifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }
    public JsonMinifier(ObjectNode json){
        super(json);
    }

    /**
     * Minify json
     * @return minified json as a String
     */
    @Override
    public String transform() throws JsonProcessingException {
        return this.jsonObjectMapper.writeValueAsString(super.rawTransform());
    }
}
