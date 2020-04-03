package grep;




import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Grep {
    File fileToWork;
    String wordToWork;


    public Grep (String word, File file) {
         this.fileToWork = file;
         this.wordToWork = word;
    }

    public Grep () {
    }
    private boolean haveOrNot(String smth, String[] words) {
        for (String word: words) {
            if (word.matches(smth)) return true;
        }
        return false;
    }

    private boolean ex (String w) {
        return w.equals("") || w == null;
    }

    public List<String> filterByWord () throws IOException {
        try {
            if (ex(wordToWork)) throw new IllegalArgumentException();
            ArrayList<String> answer = new ArrayList<String>();
            BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
            for (String line; (line = newFile.readLine()) != null; ) {
                String[] wordsInLine = line.split(" ");
                if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
            }
            newFile.close();
            return answer;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }


    public List<String> filterByRegex () throws IOException {
        if (ex(wordToWork)) throw new IllegalArgumentException();
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            String[] wordsInLine = line.split(" ");
            if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
        }
        newFile.close();
        return answer;
    }


    public List<String> invert (List<String> listToDelete) throws IOException {
        if (ex(wordToWork)) throw new IllegalArgumentException();
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            if (!listToDelete.contains(line)) answer.add(line);
        }
        newFile.close();
        return  answer;
    }

    public List<String> ignoreCase () throws IOException {
        if (ex(wordToWork)) throw new IllegalArgumentException();
        ArrayList<String> answer = new ArrayList<String>();
        FileReader read = new FileReader(fileToWork);
        BufferedReader newFile = new BufferedReader(read);
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.toLowerCase().split(" ");
            if (haveOrNot(wordToWork.toLowerCase(), wordsInLine)) answer.add(line);
        }
        newFile.close();
        return answer;
    }
}

