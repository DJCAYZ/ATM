package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

import account.Account;

public class ViewAdminEditUserPIN extends View {
    private boolean isInvalidInput;
    private Account accountToEdit;
    public ViewAdminEditUserPIN(BufferedReader input) {
        super(input);

        isInvalidInput = false;
        accountToEdit = null;
    }

    public void display() throws IOException {
        if (isInvalidInput && accountToEdit == null) {
            System.out.println(centerText("Invalid PIN. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter account's current PIN"));
        System.out.println(centerText("Enter X to Go Back"));
        System.out.println();

        if (accountToEdit == null) {
            System.out.print(centerText("", -2));
            String accountOldPIN = input.readLine();

            if (accountOldPIN.length() == 0) {
                isInvalidInput = true;
                return;
            } else if (accountOldPIN.toUpperCase().charAt(0) == 'X') {
                isInvalidInput = false;
                data.setActiveView(ViewType.ADMIN_MENU);
                return;
            }

            accountToEdit = Account.getAccountFromPin(accountOldPIN);

            if (accountToEdit == null) {
                isInvalidInput = true;
                return;
            }

            isInvalidInput = false;
        } else {
            System.out.println(centerText(accountToEdit.getPinNumber()));
        }

        if (isInvalidInput) {
            System.out.println(centerText("Invalid PIN. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Editing " + accountToEdit.getAccountName() + "'s PIN number"));
        System.out.println();

        System.out.println(centerText("Enter new PIN"));
        System.out.println(centerText("Enter X to Go Back"));

        System.out.print(centerText("", -2));
        String newPIN = input.readLine();

        if (newPIN.length() == 0) {
            isInvalidInput = true;
            return;
        } else if (newPIN.toUpperCase().charAt(0) == 'X') {
            isInvalidInput = false;
            accountToEdit = null;
            return;
        } else if (!Pattern.compile("\\d{4}").matcher(newPIN).matches()) {
            isInvalidInput = true;
            return;
        } else if (Account.getAccountFromPin(newPIN) != null) {
            isInvalidInput = true;
            return;
        }

        isInvalidInput = false;
        accountToEdit.setPinNumber(newPIN);

        System.out.println();
        System.out.println(centerText(accountToEdit.getAccountName() + "'s PIN number changed."));
        
        accountToEdit = null;

        System.out.println();
        System.out.print(centerText("Press ENTER to Go Back to Menu"));
        input.readLine();
        data.setActiveView(ViewType.ADMIN_MENU);
    }
}
