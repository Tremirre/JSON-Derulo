package pl.put.poznan.json_tool.logic.tranformer;

public class JsonKeyRetainer extends BaseJsonTransformer{
    public JsonKeyRetainer(BaseJsonTransformer nextTransform) {
        super(nextTransform);
    }

    public JsonKeyRetainer(String jsonString) {
        super(jsonString);
    }

    @Override
    public String transform() {
        return null;
    }
}
