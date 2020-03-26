package grep;



import jdk.nashorn.internal.runtime.regexp.joni.Regex;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Grep {
    File fileToWork;
    String wordToWork;
    Regex regexToWork;


    public Grep (String word, File file) {
         this.fileToWork = file;
         this.wordToWork = word;
    }

    public Grep (Regex regex, File file) {
        this.regexToWork = regex;
        this.fileToWork = file;
    }
    public Grep () {

    }
    private boolean haveOrNot(String smth, String[] words) {
        for (String word: words) {
            if (word.matches(smth)) return true;
        }
        return false;
    }

    public List<String> filterByWord () throws IOException {
        boolean flag = false;
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.split(" ");
            if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
        }
        return answer;
    }


    public List<String> filterByRegex () throws IOException {
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            String[] wordsInLine = line.split(" ");
            if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
        }
        return answer;
    }


    public List<String> invert (ArrayList<String> listToDelete) throws IOException {
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            if (!listToDelete.contains(line)) answer.add(line);
        }
        return  answer;
    }

    public List<String> ignoreCase () throws IOException {
        ArrayList<String> answer = new ArrayList<String>();
        FileReader read = new FileReader(fileToWork);
        BufferedReader newFile = new BufferedReader(read);
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.toLowerCase().split(" ");
            if (haveOrNot(wordToWork.toLowerCase(), wordsInLine)) answer.add(line);
        }
        return answer;
    }
}
