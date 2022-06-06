package pl.put.poznan.json_tool.logic.textparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.put.poznan.json_tool.logic.tranformer.JsonUnminifier;

import java.util.LinkedList;
import java.util.List;

/**
 * Class designed to compare two texts and show lines where there is a difference.
 */
public class TextComparator {
    private String s1;
    private String s2;

    /**
     * Creates an instance of a comparator.
     * @param s1 string1 to be compared
     * @param s2 string2 to be compared
     */
    public TextComparator(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Compares two texts.
     * @return list of numbers of lines where there is a difference between texts.
     */
    public List<Integer> differentLines(){

        List<Integer> linesDiff = new LinkedList<>();
        String[] lines1 = s1.split("\n");
        String[] lines2 = s2.split("\n");

        int min = Math.min(lines1.length, lines2.length);
        int max = Math.max(lines1.length, lines2.length);

        for(int i=0; i<min; i++){
            if(!lines1[i].equals(lines2[i])){
                linesDiff.add(i+1);
            }
        }

        if(min != max){
            for(int i=min; i<max; i++){
                linesDiff.add(i+1);
            }
        }
        return linesDiff;
    }
}