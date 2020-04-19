package grep;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Grep {
    private File fileToWork;
    private String wordToWork;


    Grep(String word, File file) {
        this.fileToWork = file;
        this.wordToWork = word;
        if (word.equals("") || !file.toString().matches(".+\\.txt") || file == null) throw new IllegalArgumentException("Данные введены не корректно");
    }

    private boolean haveOrNot(String smth, String[] words) {
        for (String word: words) {
            if (word.matches(smth)) return true;
        }
        return false;
    }


    public List<String> filter() throws IOException {
        try (BufferedReader newFile = Files.newBufferedReader(fileToWork.toPath(), StandardCharsets.UTF_8)) {
            ArrayList<String> answer = new ArrayList<String>();
            for (String line; (line = newFile.readLine()) != null; ) {
                String[] wordsInLine = line.split(" ");
                if (haveOrNot(wordToWork, wordsInLine)) answer.add(line);
            }
            newFile.close();
            return answer;
        }
    }


    public List<String> invert (List<String> listToDelete) throws IOException {
        try (BufferedReader newFile = Files.newBufferedReader(fileToWork.toPath(), StandardCharsets.UTF_8)) {
            ArrayList<String> answer = new ArrayList<String>();
            for (String line; (line = newFile.readLine()) != null; ) {
                if (!listToDelete.contains(line)) answer.add(line);
            }
            newFile.close();
            return answer;
        }
    }

    public List<String> ignoreCase () throws IOException {
        try (BufferedReader newFile = Files.newBufferedReader(fileToWork.toPath(), StandardCharsets.UTF_8)) {
            ArrayList<String> answer = new ArrayList<String>();
            for (String line; (line = newFile.readLine()) != null; ) {
                String[] wordsInLine = line.toLowerCase().split(" ");
                if (haveOrNot(wordToWork.toLowerCase(), wordsInLine)) answer.add(line);
            }
            newFile.close();
            return answer;
        }
    }
}
