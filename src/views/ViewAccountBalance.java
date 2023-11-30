package views;

import java.io.BufferedReader;
import java.io.IOException;

import account.Account;

public class ViewAccountBalance extends View {
    public ViewAccountBalance(BufferedReader input) {
        super(input);
    }

    public void display() throws IOException {
        Account account = data.getActiveAccount();
        System.out.println();

        System.out.println("\t\t\t\tAccount #:\t\t" + account.getAccountNumber());
        System.out.println("\t\t\t\tAccount Name:\t\t" + account.getAccountName());
        System.out.println("\t\t\t\tBalance:\t\t" + String.format("%,.2f", account.getBalance()));
        System.out.println();

        System.out.print(centerText("Press ENTER to Go Back"));
        input.readLine();
        data.setActiveView(ViewType.ACCOUNT_MENU);
    }
    
}