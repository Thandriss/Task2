package grep;




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

    private Exception ex (String w) {
        if (w.equals("") || w == null) throw new IllegalArgumentException("Не правильно введено слово или вообще не введено, попробуйте ещё раз");
        else return null;
    }

    public List<String> filterByWord () throws IOException {
        ex(wordToWork);
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null; ) {
            String[] wordsInLine = line.split(" ");
            if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
        }
        newFile.close();
        return answer;
    }


    public List<String> filterByRegex () throws IOException {
        ex(wordToWork);
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
        ex(wordToWork);
        ArrayList<String> answer = new ArrayList<String>();
        BufferedReader newFile = new BufferedReader(new FileReader(fileToWork));
        for (String line; (line = newFile.readLine()) != null;) {
            if (!listToDelete.contains(line)) answer.add(line);
        }
        newFile.close();
        return  answer;
    }

    public List<String> ignoreCase () throws IOException {
        ex(wordToWork);
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

