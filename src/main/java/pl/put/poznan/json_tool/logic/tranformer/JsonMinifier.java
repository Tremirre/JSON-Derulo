package pl.put.poznan.json_tool.logic.tranformer;

public class JsonMinifier extends BaseJsonTransformer{
    public JsonMinifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }

    public JsonMinifier(String jsonString) {
        super(jsonString);
    }

    @Override
    public String transform() {
        return null;
    }
}
