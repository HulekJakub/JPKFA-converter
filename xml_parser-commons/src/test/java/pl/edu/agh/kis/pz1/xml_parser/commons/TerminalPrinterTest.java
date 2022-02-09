package pl.edu.agh.kis.pz1.xml_parser.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class
 */
public class TerminalPrinterTest {
    TerminalPrinter terminalPrinter = new TerminalPrinter();

    @Test
    public void print() {
        terminalPrinter.print("Logger test");
        assertTrue(true);
    }

    @Test
    public void printError() {
        Exception exception = new Exception("Logger exception test");
        terminalPrinter.printError(exception);
        assertTrue(true);
    }

    @Test
    public void testPrintError() {
        Exception exception = new Exception("Logger exception test");
        terminalPrinter.printError("Logger test",exception);
        assertTrue(true);
    }
}