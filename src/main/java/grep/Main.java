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
            if (a == null) {
                System.out.println("Что-то пошло не так");
                System.exit(1);
            }
            if (data.isInvertFlag() && !data.getWord().isEmpty()) {
                a = new Grep(data.getWord(), data.getFile()).invert(a);
            }
            for (String i: a) {
                System.out.println(i);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.exit(1);
        }
    }
}
