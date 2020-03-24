package grep;



import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.*;
import java.util.ArrayList;

public class grep {
    String fileToWork;
    String wordToWork;
    Regex regexToWork;


    grep (String word, String file) {
         this.fileToWork = file;
         this.wordToWork = word;
    }

    grep (Regex regex, String file) {
        this.regexToWork = regex;
        this.fileToWork = file;
    }
    grep () {

    }

    ArrayList<String> filterByWord () throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.split(" ");
            for (String someWord: wordsInLine) {
                if (someWord.equals(wordToWork)) {
                    flag = true;
                    break;
                } else flag = false;
            }
            if (flag) answer.add(line);
        }
        return answer;
    }


    public ArrayList<String> filterByRegex () throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            String[] wordsInLine = line.split(" ");
            for (String someWord: wordsInLine) {
                if (someWord.matches(regexToWork.toString())) {
                    flag = true;
                    break;
                } else flag = false;
            }
            if (flag) answer.add(line);
        }
        return answer;
    }


    public ArrayList<String> invert (ArrayList<String> listToDelete) throws IOException {
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            if (!listToDelete.contains(line)) answer.add(line);
        }
        return  answer;
    }

    public ArrayList<String> ignoreCase () throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.split(" ");
            for (String someWord: wordsInLine) {
                if (someWord.toLowerCase().equals(wordToWork)) {
                    flag = true;
                    break;
                } else flag = false;
            }
            if (flag) answer.add(line);
        }
        return answer;
    }
}
