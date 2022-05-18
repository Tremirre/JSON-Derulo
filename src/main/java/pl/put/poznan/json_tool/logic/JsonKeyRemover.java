package pl.put.poznan.json_tool.logic;

public class JsonKeyRemover extends BaseJsonTransformer{
    public JsonKeyRemover(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }

    public JsonKeyRemover(String jsonString) {
        super(jsonString);
    }

    @Override
    public String transform() {
        return null;
    }
}
