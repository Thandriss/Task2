package grep;



import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        try {
            ComLine data = new ComLine(args);
            List<String> a = null;
            if (((data.isRegex() && !data.getWord().isEmpty()) || !data.getWord().isEmpty()) && data.getFile().toString().matches(".+\\.txt")) {
                if (data.isFlag()) {
                    a = new Grep(data.getWord(), data.getFile()).ignoreCase();
                } else {
                    a = new Grep(data.getWord(), data.getFile()).filter();
                }
            } else {
                System.err.println("Что-то пошло не так");
            }
            if (data.isInvertFlag() && !data.getWord().isEmpty()) {
                a = new Grep(data.getWord(), data.getFile()).invert(a);
            } else {
                System.err.println("Что-то пошло не так");
            }
            for (String i: a) {
                System.out.println(i);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
