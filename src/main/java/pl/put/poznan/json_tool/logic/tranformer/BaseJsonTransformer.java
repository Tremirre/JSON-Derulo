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
        this.jsonObjectMapper = previousTransformer.getMapper();
        this.previousTransformer = previousTransformer;
    }
    public BaseJsonTransformer(ObjectMapper mapper, ObjectNode json){
        this.jsonObjectMapper = mapper;
        this.jsonObjectNode = json;
    }

    /**
     * Performs transformation and returns a json in ObjectNode form.
     * @return transformed json in ObjectNode form
     */
    public ObjectNode rawTransform() {
        return this.previousTransformer != null ? this.previousTransformer.rawTransform() : this.jsonObjectNode;
    }

    /**
     * Performs transformation and returns a json in String form.
     * @return transformed json in String form
     * @throws JsonProcessingException exception on transform
     */
    public abstract String transform() throws JsonProcessingException;

    public ObjectMapper getMapper() {
        return this.jsonObjectMapper;
    }
}
