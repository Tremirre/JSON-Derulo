package pl.put.poznan.json_tool.logic.textparsing;

import java.util.LinkedList;
import java.util.List;

/**
 * Class designed to find all lines in the text that contain a specified string.
 */
public class TextFinder {
    private String text;
    private String s;

    /**
     * Creates an instance of TextFinder
     * @param text text that will be searched
     * @param s string to find in the text
     */
    public TextFinder(String text, String s){
        this.text = text;
        this.s = s;
    }

    /**
     * Finds lines that contain a pre-defined string.
     * @return list of numbers of lines that contain a specified string.
     */
    public List<Integer> findLines(){
        String[] lines = text.split("\n");
        List<Integer> linesContain = new LinkedList<>();

        for(int i=0; i<lines.length; i++){
            if(lines[i].contains(s)){
                linesContain.add(i+1);
            }
        }
        return linesContain;
    }
}
