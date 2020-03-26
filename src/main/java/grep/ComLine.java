package grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;


public class ComLine {
    @Option(name = "-r", metaVar = "Regex", usage = "Give regex to find all lines", forbids = "-i")
    boolean regex;

    @Argument(metaVar = "Word", required = true, usage = "Give word to find all lines")
    String word;

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignore case")
    boolean flag = true;

    @Option(name = "-v", metaVar = "Name", usage = "name")
    boolean invertFlag = true;

    @Argument(required = true, metaVar = "in", usage = "input")
    File file;

    public ComLine(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try{
            parser.parseArgument(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Неверно указан параметр. Попробуйте еще раз");
        }
    }
}
