package pl.put.poznan.json_tool.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.json_tool.logic.textparsing.TextComparator;
import pl.put.poznan.json_tool.logic.textparsing.TextFinder;
import pl.put.poznan.json_tool.logic.tranformer.BaseJsonTransformer;
import pl.put.poznan.json_tool.logic.tranformer.JsonUnminifier;
import pl.put.poznan.json_tool.logic.utils.JsonTransormationsWrapper;

import java.util.List;

/**
 * Handle http requests for json transformations and the comparison of two json files.
 */
@RestController
@RequestMapping("/json")
public class JSONToolController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolController.class);

    /**
     * Retrieves a json from a http POST request and processes it according to user-defined parameters.
     * @param json json file to be transformed
     * @param actions actions to be performed (in a string form, comma-separated)
     * @param keys keys to be removed or retained (in a string form, comma-separated)
     * @return transformed json in the string form
     */
    @RequestMapping(value="/transform/{text}", method = RequestMethod.POST, produces = "application/json")
    public String post( @RequestBody ObjectNode json,
                        @RequestParam(value = "actions", defaultValue = "") String actions,
                        @RequestParam(value = "keys", defaultValue = "") String keys){

        // log the parameters
        logger.debug(String.valueOf(json));
        logger.debug(String.valueOf(actions));
        logger.debug(String.valueOf(keys));

        // perform the transformation
        JsonTransormationsWrapper wrapper = new JsonTransormationsWrapper(actions.split(","), keys.split(","));
        try{
            BaseJsonTransformer transformer = wrapper.getTransformer(json);
            return transformer.transform();
        }
        catch(IllegalArgumentException | JsonProcessingException e){
            return e.getMessage();
        }
    }

    /**
     * Compares two json files and shows lines where there is a difference.
     * @param jsons list of json files to be compared
     * @return list of numbers of lines where there is a difference between json files
     * @throws JsonProcessingException if the files provided are not valid jsons
     */
    @RequestMapping(value="/compare", method = RequestMethod.POST, produces = "application/json")
    public List<Integer> post(@RequestBody ObjectNode[] jsons) throws JsonProcessingException {

        // log the parameters
        logger.debug(String.valueOf(jsons[0]));
        logger.debug(String.valueOf(jsons[1]));

        // perform the transformation
        TextComparator comparator = new TextComparator((new JsonUnminifier(jsons[0])).transform(),
                (new JsonUnminifier(jsons[1])).transform());
        return comparator.differentLines();
    }

    /**
     * Finds lines that contain a pre-defined string in the text.
     * @param text text to be searched
     * @param string string to be looked up in the text
     * @return list of numbers of lines that contain a specified string.
     */
    @RequestMapping(value="/find", method = RequestMethod.POST, produces = "application/json")
    public List<Integer> post(@RequestBody String text,
                              @RequestParam(value = "string", defaultValue = "") String string) {

        // log the parameters
        logger.debug(text);
        logger.debug(string);

        // perform the transformation
        TextFinder finder = new TextFinder(text, string);
        return finder.findLines();
    }
}
