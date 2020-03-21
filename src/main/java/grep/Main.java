package grep;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.File;

public class Main {
    public static void main (String[] args) {
        ComLine data = new ComLine(args);
        System.out.println(data.regex);
        System.out.println(data.word);
        System.out.println(data.flag);
        System.out.println(data.invertFlag);
        System.out.println(data.file);
        String weWillWork = data.file;
        Regex regex = data.regex;
        String word = data.word;
        grep newOne = new grep(word, weWillWork);
    }
}
