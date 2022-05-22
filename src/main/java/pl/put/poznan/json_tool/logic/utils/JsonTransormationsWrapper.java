package pl.put.poznan.json_tool.logic.utils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pl.put.poznan.json_tool.logic.tranformer.*;

/**
 * Create a json decorator based on a list of parameters.
 */
public class JsonTransormationsWrapper {

    private String[] actions;
    private String[] keys;

    /**
     * Creates an instance of JsonTransormationsWrapper.
     * @param actions actions to be performed (in a string form, comma-separated)
     * @param keys keys to be removed or retained (in a string form, comma-separated)
     */
    public JsonTransormationsWrapper(String[] actions, String[] keys) {
        this.actions = actions;
        this.keys = keys;
    }

    /**
     * Wraps the json transformer with the next action to be performed.
     * @param transformer json transformer to be wrapped
     * @param action next action to be performed
     * @return wrapped json transformer
     */
    public BaseJsonTransformer addAction(BaseJsonTransformer transformer, String action) throws IllegalArgumentException {
        switch (action) {
            case "minify": return new JsonMinifier(transformer);
            case "unminify": return new JsonUnminifier(transformer);
            case "remove": return new JsonKeyRemover(transformer, this.keys);
            case "retain": return new JsonKeyRetainer(transformer, this.keys);
            default: throw new IllegalArgumentException("Invalid action provided!\nThe following actions are acceptable: minify, unminify, remove, retain.");
        }
    }

    /**
     * Creates a json decorator based on a list of actions to be performed on a json file.
     * @param json json file
     * @return json decorator
     */
    public BaseJsonTransformer getTransformer(ObjectNode json) throws IllegalArgumentException {

        BaseJsonTransformer transformer = new JsonUnminifier(json);
        for(String action: this.actions){
            transformer = addAction(transformer, action);
        }
        return transformer;
    }
}
