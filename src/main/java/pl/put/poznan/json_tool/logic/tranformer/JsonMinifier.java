package pl.put.poznan.json_tool.logic.tranformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMinifier extends BaseJsonTransformer{
    public JsonMinifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }
    public JsonMinifier(String jsonString) {
        super(jsonString);
    }

    @Override
    public String transform() throws JsonProcessingException {
        if(this.previousTransformer != null) {
            this.jsonString = this.previousTransformer.transform();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = (ObjectNode)mapper.readTree(this.jsonString);
        return mapper.writeValueAsString(objectNode);
    }
}
