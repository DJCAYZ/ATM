package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewStartMenu extends View {
    private boolean isInvalidInput;
    public ViewStartMenu(BufferedReader input) {
        super(input);
        isInvalidInput = false;
    }

    public void display() throws IOException {
        if (isInvalidInput) {
            System.out.println(centerText("Invalid input. Please try again."));
        } else {
            System.out.println();
        }
        System.out.println(centerText("S -> Start Transaction"));
        System.out.println(centerText("Q -> Quit", -6));
        System.out.println();

        System.out.print(centerText("Enter your choice: ", -1));
        String userChoice = input.readLine();

        if (userChoice.length() == 0) {
            isInvalidInput = true;
            return;
        }

        switch(userChoice.toUpperCase().charAt(0)) {
            case 'S':
                data.setActiveView(ViewType.LOGIN);
                break;
            case 'Q':
                data.setActiveView(ViewType.EXITING);
                break;
            default:
                isInvalidInput = true;
                return;
        }

        isInvalidInput = false;
    }
}
