package pl.put.poznan.json_tool.logic;

public class JsonUnminifier extends BaseJsonTransformer{
    public JsonUnminifier(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }

    public JsonUnminifier(String jsonString) {
        super(jsonString);
    }

    @Override
    public String transform() {
        return null;
    }
}
