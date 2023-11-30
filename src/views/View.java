package views;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class View {
    private static final int OUTPUT_WIDTH = 100;
    private static final String SHORT_NAME = "OOPBC";
    private static final String LONG_NAME = "Object Oriented Programming Banking Corporation";

    protected ViewData data;
    protected BufferedReader input;

    public View(BufferedReader inputReader) {
        this.input = inputReader;
        this.data = ViewData.getInstance();
    }
    
    public abstract void display() throws IOException;

    public void render() {
        printHorizontalLine('-');
        clearScreen();
        printHeader();

        try {
            display();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Text formatting methods
    protected static void printHeader() {
        System.out.println(centerText(SHORT_NAME));
        System.out.println(centerText(LONG_NAME));
        printHorizontalLine('-');
    }

    protected static void printHorizontalLine(char lineChar) {
        for (int i = 0; i < OUTPUT_WIDTH; i++) {
            System.out.print(lineChar);
        }

        System.out.println();
    }

    protected static String centerText(String text) {
        return centerText(text, 0);
    }

    protected static String centerText(String text, int offset) {
        int numOfSpaces = ((OUTPUT_WIDTH - text.length() - 2) / 2) + offset;
        String spaces = "";

        for (int i = 0; i < numOfSpaces; i++) {
            spaces += ' ';
        }

        return spaces + " " + text;
    }

    protected static void clearScreen() {
        System.out.print("\033[H\033[2J");
        // try {
        //     if (System.getProperty("os.name").contains("Windows"))
        //         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //     else
        //         Runtime.getRuntime().exec("clear");
        // } catch (IOException | InterruptedException ex) {
        //     System.err.println(ex.getMessage());
        // }
    }
}
