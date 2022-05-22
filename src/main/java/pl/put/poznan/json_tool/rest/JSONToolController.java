package pl.put.poznan.json_tool.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.json_tool.logic.tranformer.BaseJsonTransformer;
import pl.put.poznan.json_tool.logic.utils.JsonTransormationsWrapper;

import java.util.Arrays;

@RestController
@RequestMapping("/json")
public class JSONToolController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolController.class);

//    TODO - do wyrzucenia
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
//        JSONTool transformer = new JSONTool(transforms);
//        return transformer.transform(text);
        return "";
    }

    @RequestMapping(value="/transform/{text}", method = RequestMethod.POST, produces = "application/json")
    public String post( @PathVariable String text,
                        @RequestBody ObjectNode json,
                        @RequestParam(value = "actions", defaultValue = "") String actions,
                        @RequestParam(value = "keys", defaultValue = "") String keys) throws JsonProcessingException {

        // log the parameters
        logger.debug(String.valueOf(json));
        logger.debug(String.valueOf(actions));
        logger.debug(String.valueOf(keys));

        // perform the transformation
        JsonTransormationsWrapper wrapper = new JsonTransormationsWrapper(actions.split(","), keys.split(","));
        BaseJsonTransformer transformer = wrapper.getTransformer(json);
        return transformer.transform();
    }
}
