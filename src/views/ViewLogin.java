package views;

import java.io.BufferedReader;
import java.io.IOException;

import account.Account;

public class ViewLogin extends View {
    private int incorrectLoginAttempts;
    public ViewLogin(BufferedReader input) {
        super(input);

        incorrectLoginAttempts = 0;
    }

    public void display() throws IOException {
        if (incorrectLoginAttempts > 0) {
            System.out.println(centerText("Incorrect PIN number. Please try again."));
        } else {
            System.out.println();
        }
        
        System.out.println(centerText("Enter your PIN number:"));
        System.out.println();
        System.out.print(centerText("", -2));
        String pinAttempt = input.readLine();
        
        Account account = Account.getAccountFromPin(pinAttempt);
        
        if (account == null) {
            incorrectLoginAttempts++;
            
            if (incorrectLoginAttempts == 3) {
                data.setActiveView(ViewType.LOGIN_FAILED);
            }
            return;
        }
        
        incorrectLoginAttempts = 0;
        data.setActiveAccount(account);
        data.setActiveView(ViewType.ACCOUNT_MENU);
    }
}
