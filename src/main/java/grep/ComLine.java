package grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;


public class ComLine {

    @Option(name = "-r", metaVar = "Regex", usage = "Give regex to find all lines", forbids = "-i")
    private boolean regex;

    @Argument(metaVar = "Word", required = true, usage = "Give word to find all lines")
    private String word;

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignore case")
    private boolean flag;

    @Option(name = "-v", metaVar = "invert", usage = "invert")
    private boolean invertFlag;

    @Argument(required = true, metaVar = "inFile", index = 1, usage = "input File")
    private File file;

    public String getWord() {
        return word;
    }

    public boolean isInvertFlag() {
        return invertFlag;
    }

    public boolean isFlag() {
        return flag;
    }

    public File getFile() {
        return file;
    }

    public boolean isRegex() {
        return regex;
    }

    public ComLine(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try{
            int len = args.length;
            if (len >= 2 && len <= 4) {
                boolean wrongFile = args[len - 1].matches(".+\\.txt");
                boolean d = args[len - 2].matches("[a-zA-Zа-яА-ЯёЁ]+");
                if (!wrongFile || !d) {
                    System.out.println("Неверно указан параметр. Попробуйте еще раз");
                    System.exit(1);
                }
                if (len >= 3) {
                    if (!args[0].equals("-i") && !args[0].equals("-r") && !args[0].equals("-v")) {
                        System.out.println("Неверно указан параметр. Попробуйте еще раз");
                        System.exit(1);
                    }
                    if (len == 4 && (!args[1].equals("-i") && !args[1].equals("-r") && !args[1].equals("-v"))) {
                        System.out.println("Неверно указан параметр. Попробуйте еще раз");
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("Допишите или уберите один из параметров");
                System.exit(1);
            }
            parser.parseArgument(args);
        } catch (IllegalArgumentException | CmdLineException e) {
            System.err.println("Неверно указан параметр. Попробуйте еще раз");
        }
    }
}
