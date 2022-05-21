package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *  Get the full structure in JSON format from minified JSON format to improve the readability of the data
 */
public class JsonUnminifier extends BaseJsonTransformer {
    public JsonUnminifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }
    public JsonUnminifier(String jsonString) {
        super(jsonString);
    }

    /**
     * Unminify json
     * @return full json
     * @throws JsonProcessingException
     */
    @Override
    public String transform() throws JsonProcessingException {
        if(this.previousTransformer != null) {
            this.jsonString = this.previousTransformer.transform();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = (ObjectNode)mapper.readTree(this.jsonString);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
    }
}
