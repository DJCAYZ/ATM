package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewAccountWithdrawalFailed extends View {
    public ViewAccountWithdrawalFailed(BufferedReader input) {
        super(input);
    }

    public void display() throws IOException {
        System.out.println();
        System.out.println(centerText("CARD CAPTURED"));
        System.out.println(centerText("PLEASE CALL 143-44"));
        System.out.println();
        System.out.print(centerText("Press ENTER to go back"));
        input.readLine();

        data.setActiveAccount(null);
        data.setActiveView(ViewType.START_MENU);
    }
}
