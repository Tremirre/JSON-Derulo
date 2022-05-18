package pl.put.poznan.json_tool.logic.tranformer;

public abstract class BaseJsonTransformer {
    protected BaseJsonTransformer previousTransformer;
    protected String jsonString;
    public BaseJsonTransformer(BaseJsonTransformer nextTransform) {
        previousTransformer = nextTransform;
    }
    public BaseJsonTransformer(String jsonString) {
        this.jsonString = jsonString;
    }
    public abstract String transform();
}
