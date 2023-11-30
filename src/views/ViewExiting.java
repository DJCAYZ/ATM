package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewExiting extends View {
    public ViewExiting(BufferedReader input) {
        super(input);
    }

    public void display() throws IOException {
        System.out.println(centerText("Exiting..."));
    }
}
