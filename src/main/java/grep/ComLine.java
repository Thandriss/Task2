package grep;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class ComLine {
    @Option(name = "-r", metaVar = "Regex", required = true, usage = "Give regex to find all lines", forbids = "-i")
    Regex regex;

    @Option(name = "word", metaVar = "Word", required = true, usage = "Give word to find all lines")
    String word;

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignore case")
    boolean flag;

    @Option(name = "-v", metaVar = "Name", usage = "name")
    boolean invertFlag;

    @Argument(required = true, metaVar = "in", usage = "input")
    String file;

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
