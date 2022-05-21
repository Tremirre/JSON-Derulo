package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Abstract class for parsing jsons
 */
public abstract class BaseJsonTransformer {
    protected BaseJsonTransformer previousTransformer;
    protected String jsonString;

    public BaseJsonTransformer(BaseJsonTransformer nextTransform) {
        previousTransformer = nextTransform;
    }
    public BaseJsonTransformer(String jsonString) {
        this.jsonString = jsonString;
    }

    /**
     * perform transformation
     * @return transformed json
     * @throws JsonProcessingException
     */
    public abstract String transform() throws JsonProcessingException;

}
