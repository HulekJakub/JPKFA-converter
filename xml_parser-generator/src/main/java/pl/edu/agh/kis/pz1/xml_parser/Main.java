package pl.edu.agh.kis.pz1.xml_parser;

import pl.edu.agh.kis.pz1.xml_parser.commons.TerminalPrinter;
import pl.edu.agh.kis.pz1.xml_parser.generator.Generator;

import java.io.IOException;

/**
 * Main class for xml parsing program
 */
public class Main {
    /**
     * Console logger
     */
    public static final TerminalPrinter terminalPrinter = new TerminalPrinter();

    /**
     * Converts csv/xlsx file containing invoice data in specific format.
     * Creates new xml file if it did not exist, otherwise overwrites existing file.
     * @param args 2 relative paths to one csv/xlsx file containing data and to one xml file destination
     */
    public static void main(String[] args){
        if(argsInvalid(args)){
            terminalPrinter.print("Please rerun with paths to csv/xlsx file and xml file.");
            return;
        }

        Generator generator = new Generator(args[0], args[1]);
        try {
            terminalPrinter.print(generator.createXmlFile());
            boolean isOk = generator.filesAreGood();
            if (!isOk) {
                terminalPrinter.print("File are not OK. Terminating...");
                return;
            }
            terminalPrinter.print("File names are OK.");
            boolean generated = generator.generate();
            if(generated){
                terminalPrinter.print("File generated correctly.");
            } else {
                terminalPrinter.print("File was not generated. Something went wrong.");
            }
        } catch (IOException ioException){
            terminalPrinter.print("Something went wrong. Terminating because:\n" + ioException.getMessage());
        }

    }

    private static boolean argsInvalid(String[] args){
        return args.length < 2 || !args[0].matches(".*\\.(csv|xlsx)$") || !args[1].matches(".*\\.xml$");
    }

    private Main(){}
}
