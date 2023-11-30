package views;

import java.io.BufferedReader;
import java.io.IOException;

import account.Account;
import account.AccountDepositException;

public class ViewAccountDeposit extends View {
    private boolean isInvalidInput;
    private String reason;
    
    public ViewAccountDeposit(BufferedReader input) {
        super(input);

        isInvalidInput = false;
        reason = null;
    }

    public void display() throws IOException {
        Account account = data.getActiveAccount();
        if (isInvalidInput) {
            System.out.println("Invalid input. " + reason);
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter amount to be deposited"));
        System.out.println();

        System.out.print(centerText("", -4));
        String strDepositAmount = input.readLine();

        if (strDepositAmount.length() == 0) {
            isInvalidInput = true;
            reason = "Not a valid number.";
        } else if (strDepositAmount.toUpperCase().charAt(0) == 'X') {
            isInvalidInput = false;
            reason = null;
            data.setActiveView(ViewType.ACCOUNT_MENU);
            return;
        }

        try {
            double depositAmount = Double.parseDouble(strDepositAmount);
            account.depositAmount(depositAmount);
        } catch (NumberFormatException e) {
            isInvalidInput = true;
            reason = "Not a valid number.";
            return;
        } catch (AccountDepositException e) {
            isInvalidInput = true;
            reason = e.getMessage();
            return;
        }

        System.out.println(centerText("New Balance: " + String.format("%,.2f", account.getBalance())));
        System.out.println();

        System.out.print(centerText("Press ENTER to Go Back"));
        input.readLine();

        isInvalidInput = false;
        reason = null;

        data.setActiveView(ViewType.ACCOUNT_MENU);
    }
}
