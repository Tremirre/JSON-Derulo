package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * An abstract class for transforming json in multiple ways.
 */
public abstract class BaseJsonTransformer {
    protected BaseJsonTransformer previousTransformer;
    protected ObjectMapper jsonObjectMapper;
    protected ObjectNode jsonObjectNode;

    public BaseJsonTransformer(BaseJsonTransformer previousTransformer) {
        this.jsonObjectMapper = new ObjectMapper();
        this.previousTransformer = previousTransformer;
    }
    public BaseJsonTransformer(ObjectNode json){
        this.jsonObjectMapper = new ObjectMapper();
        this.jsonObjectNode = json;
    }

    /**
     * Performs transformation and returns a json in ObjectNode form.
     * @return transformed json in ObjectNode form
     */
    protected ObjectNode rawTransform() {
        return this.previousTransformer != null ? this.previousTransformer.rawTransform() : this.jsonObjectNode;
    }

    /**
     * Performs transformation and returns a json in String form.
     * @return transformed json in String form
     * @throws JsonProcessingException exception on transform
     */
    public abstract String transform() throws JsonProcessingException;
}
