package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Abstract class for parsing jsons
 */
public abstract class BaseJsonTransformer {
    protected BaseJsonTransformer previousTransformer;
    protected ObjectMapper jsonObjectMapper;
    protected ObjectNode jsonObjectNode;

    public BaseJsonTransformer(BaseJsonTransformer previousTransformer) {
        this.jsonObjectMapper = new ObjectMapper();
        this.previousTransformer = previousTransformer;
    }
    public BaseJsonTransformer(String jsonString) throws JsonProcessingException {
        this.jsonObjectMapper = new ObjectMapper();
        this.jsonObjectNode = (ObjectNode)this.jsonObjectMapper.readTree(jsonString);
    }

    protected ObjectNode rawTransform() {
        return this.previousTransformer != null ? this.previousTransformer.rawTransform() : this.jsonObjectNode;
    }

    /**
     * perform transformation
     * @return transformed json
     */
    public abstract String transform() throws JsonProcessingException;
}
