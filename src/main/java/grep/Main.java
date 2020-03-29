package grep;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) throws IOException {
        ComLine data = new ComLine(args);
        Grep newOne = new Grep(data.word, data.file);
        ArrayList<String> a;
        if (data.word != null) {
            if (data.flag) {
                a = (ArrayList<String>) newOne.ignoreCase();
            } else {
                a = (ArrayList<String>) newOne.filterByWord();
            }
        } else {
            a = (ArrayList<String>) newOne.filterByRegex();
        }
        if (data.invertFlag) {
            a = (ArrayList<String>) newOne.invert(a);
            for (String i: a){
                System.out.println(i);
            }
        } else {
            for (String i: a){
                System.out.println(i);
            }
        }
    }
}
