package grep;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) throws IOException {
        ComLine data = new ComLine(args);
        grep newOne = new grep();
        if (data.word != null && data.file != null) newOne = new grep(data.word, data.file);
        else if (data.regex != null && data.file != null) newOne = new grep(data.regex, data.file);
        else System.out.println("Данные введены не корректно");
        ArrayList<String> a = new ArrayList<String>();
        boolean flag = true;
        if (data.invertFlag) {
            if (data.flag) {
                if (data.word != null)a = newOne.ignoreCase(); else System.out.println("Не корректно введены данные");
            } else if (data.word != null) {
                a = newOne.filterByWord();
            } else if (data.regex != null) {
                a = newOne.filterByRegex();
            } else flag = false;
            if (flag) {
                for (String toPrint : newOne.invert(a)) {
                    System.out.println(toPrint);
                }
            }
        } else if (data.flag) {
            if (data.word != null) a = newOne.ignoreCase(); else flag = false; System.out.println("Не корректно введены данные");
            if (flag) for (String toPrint: newOne.invert(a)) System.out.println(toPrint);
        } else if (data.word != null) {
            a = newOne.filterByWord();
            for (String toPrint : newOne.invert(a)) {
                System.out.println(toPrint);
            }
        } else if (data.regex != null) {
            a = newOne.filterByRegex();
            for (String toPrint : newOne.invert(a)) {
                System.out.println(toPrint);
            }
        } else System.out.println("Что-то пошло не так, попробуйте ещё раз");
    }
}
