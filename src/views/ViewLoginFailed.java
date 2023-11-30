package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewLoginFailed extends View {
    public ViewLoginFailed(BufferedReader input) {
        super(input);
    }

    public void display() throws IOException {
        System.out.println();
        System.out.println(centerText("CAPTURED CARD"));
        System.out.println(centerText("PLEASE 143-44"));
        System.out.println();
        System.out.print(centerText("Press ENTER to go back"));
        input.readLine();
        data.setActiveView(ViewType.START_MENU);
    }
}
