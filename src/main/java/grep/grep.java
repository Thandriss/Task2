package grep;



import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.*;
import java.util.ArrayList;

public class grep {
    String inputName;
    String file;
    grep (String input, String file) {
        this.file = file;
        this.inputName = input;
    }
    public ArrayList<String> fillterByWord (String word, String file) throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(file));
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.split(" ");
            for (String someWord: wordsInLine) {
                if (someWord.equals(word)) {
                    flag = true;
                    break;
                } else flag = false;
            }
            if (flag) answer.add(line);
        }
        return answer;
    }


    public ArrayList<String> fillerByRegex (Regex r, String file) throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(file));
        for (String line; (line = newFile.readLine()) != null;) {
            String[] wordsInLine = line.split(" ");
            for (String someWord: wordsInLine) {
                if (someWord.matches(r.toString())) {
                    flag = true;
                    break;
                } else flag = false;
            }
            if (flag) answer.add(line);
        }
        return answer;
    }
}
