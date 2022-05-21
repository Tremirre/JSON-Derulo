package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Get a minified JSON structure from JSON full format to reduce data size
 */
public class JsonMinifier extends BaseJsonTransformer {
    public JsonMinifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }
    public JsonMinifier(String jsonString) throws JsonProcessingException {
        super(jsonString);
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
