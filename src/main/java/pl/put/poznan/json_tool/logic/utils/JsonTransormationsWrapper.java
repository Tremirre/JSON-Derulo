package pl.put.poznan.json_tool.logic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pl.put.poznan.json_tool.logic.tranformer.*;

public class JsonTransormationsWrapper {

    private String[] actions;
    private String[] keys;

    public JsonTransormationsWrapper(String[] actions, String[] keys) {
        this.actions = actions;
        this.keys = keys;
    }

    public BaseJsonTransformer addAction(BaseJsonTransformer transformer, String action) {
        switch (action) {
            case "minify": return new JsonMinifier(transformer);
            case "unminify": return new JsonUnminifier(transformer);
            case "remove": return new JsonKeyRemover(transformer, this.keys);
            case "retain": return new JsonKeyRetainer(transformer, this.keys);
//            TODO - throw an exception - invalid action
            default: return transformer;
        }
    }

    public BaseJsonTransformer getTransformer(ObjectNode json){

        BaseJsonTransformer transformer = new JsonUnminifier(json);
        for(String action: this.actions){
            transformer = addAction(transformer, action);
        }
        return transformer;
    }
}
