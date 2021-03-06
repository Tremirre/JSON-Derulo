package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *  Get the full structure in JSON format from minified JSON format to improve the readability of the data.
 */
public class JsonUnminifier extends BaseJsonTransformer {
    public JsonUnminifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }
    public JsonUnminifier(ObjectMapper mapper, ObjectNode json){
        super(mapper, json);
    }

    /**
     * Unminifies json.
     * @return full, unfinified json as a String
     * @throws JsonProcessingException thrown on object node to string conversion
     */
    @Override
    public String transform() throws JsonProcessingException {
        return this.jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(super.rawTransform());
    }
}
