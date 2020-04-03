package grep;


import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        ComLine data = new ComLine(args);
        Grep newOne = new Grep(data.getWord(), data.getFile());
        List<String> a;
        if (data.isRegex()) {
            a = newOne.filterByRegex();
        } else {
            if (data.isFlag()) {
                a = newOne.ignoreCase();
            } else {
                a = newOne.filterByWord();
            }
        }
        if (data.isInvertFlag()) {
            a = newOne.invert(a);
        }
        for (String i: a) {
            System.out.println(i);
        }
    }
}
