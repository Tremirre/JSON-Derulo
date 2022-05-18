package pl.put.poznan.json_tool.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.put.poznan.json_tool.logic.JsonTransformer;

import java.util.LinkedList;
import java.util.Queue;

public class JsonsComparator {
    private LinkedList<Integer> linesDiff;

    public JsonsComparator(){
        this.linesDiff = new LinkedList<>();
    }

    public LinkedList<Integer> differentLines(String s1, String s2) throws JsonProcessingException {
        JsonTransformer transformer = new JsonTransformer();

        transformer.setNode(s1);
        String[] lines1 = transformer.unminify().split("\n");
        transformer.setNode(s2);
        String[] lines2 = transformer.unminify().split("\n");

//        perform padding
//        int lengthDiff = lines1.length - lines2.length;
//        if(lengthDiff < 0){
//            for
//        }
//        blabla...

        for(int i=0; i< lines1.length; i++){
            if(!lines1[i].equals(lines2[i])){
                linesDiff.add(i+1);
            }
        }
        return linesDiff;
    }
}