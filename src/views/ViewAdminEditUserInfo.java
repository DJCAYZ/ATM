package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

import account.Account;

enum InfoToEdit {
    ACCOUNT_NUMBER,
    ACCOUNT_NAME,
    BALANCE
}

public class ViewAdminEditUserInfo extends View {
    private boolean isInvalidInput;
    private Account accountToEdit;
    private InfoToEdit infoToEdit;

    public ViewAdminEditUserInfo(BufferedReader input) {
        super(input);

        isInvalidInput = false;
        accountToEdit = null;
        infoToEdit = null;
    }

    public void display() throws IOException {
        if (isInvalidInput && accountToEdit == null) { 
            System.out.println(centerText("Invalid PIN. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter PIN of account to edit"));
        System.out.println(centerText("Enter X to exit"));
        System.out.println();

        if (accountToEdit == null) {
            System.out.print(centerText("", -2));
            String accountPIN = input.readLine();
            
            if (accountPIN.length() == 0) {
                isInvalidInput = true;
                return;
            } else if (accountPIN.toUpperCase().charAt(0) == 'X') {
                isInvalidInput = false;
                data.setActiveView(ViewType.ADMIN_MENU);
                return;
            }

            accountToEdit = Account.getAccountFromPin(accountPIN);

            if (accountToEdit == null) {
                isInvalidInput = true;
                return;
            }

            isInvalidInput = false;
        } else {
            System.out.println(centerText(accountToEdit.getPinNumber()));
        }

        System.out.println();

        if (isInvalidInput && infoToEdit == null) {
            System.out.println(centerText("Invalid input. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Editing " + accountToEdit.getAccountName() + "'s account."));
        System.out.println();
        System.out.println(centerText("What information will be edited?"));
        System.out.println(centerText("A -> Account Number", -1));
        System.out.println(centerText("N -> Account Name", -2));
        System.out.println(centerText("B -> Balance", -5));
        System.out.println(centerText("X -> Reselect Account"));
        System.out.println();

        if (infoToEdit == null) {
            System.out.print(centerText("Enter your choice: ", -1));
            String userChoice = input.readLine();

            if (userChoice.length() == 0) {
                isInvalidInput = true;
                return;
            }

            switch(userChoice.toUpperCase().charAt(0)) {
                case 'A':
                    infoToEdit = InfoToEdit.ACCOUNT_NUMBER;
                    break;
                case 'N':
                    infoToEdit = InfoToEdit.ACCOUNT_NAME;
                    break;
                case 'B':
                    infoToEdit = InfoToEdit.BALANCE;
                    break;
                case 'X':
                    accountToEdit = null;
                    isInvalidInput = false;
                    return;
                default:
                    isInvalidInput = true;
                    return;
            }

            isInvalidInput = false;
        } else {
            System.out.println(centerText("Enter your choice: " + (infoToEdit == InfoToEdit.ACCOUNT_NUMBER ? "A" : (infoToEdit == InfoToEdit.ACCOUNT_NAME ? "N" : "B")), -1));
        }

        System.out.println();

        if (isInvalidInput) {
            System.out.println(centerText("Invalid input. Please try again."));
        } else {
            System.out.println();
        }

        switch (infoToEdit) {
            case ACCOUNT_NUMBER:
                System.out.println(centerText("Editing Account Number"));
                System.out.println(centerText("Enter X to Go Back"));
                System.out.println(centerText("Format: ####-####-####"));
                System.out.println();

                System.out.print(centerText("", -7));
                String newAccountNumber = input.readLine();

                if (newAccountNumber.length() == 0) {
                    isInvalidInput = true;
                    return;
                } else if (newAccountNumber.toUpperCase().charAt(0) == 'X') {
                    isInvalidInput = false;
                    infoToEdit = null;
                    return;
                } else if (!Pattern.compile("\\d{4}-\\d{4}-\\d{4}").matcher(newAccountNumber).matches()) {
                    isInvalidInput = true;
                    return;
                }

                accountToEdit.setAccountNumber(newAccountNumber);

                System.out.println();
                System.out.println(centerText("New Account Number: " + accountToEdit.getAccountNumber()));
                break;

            case ACCOUNT_NAME:
                System.out.println(centerText("Editing Account Name"));
                System.out.println(centerText("Enter X to Go Back"));
                System.out.println();

                System.out.print(centerText("", -10));
                String newAccountName = input.readLine();

                if (newAccountName.length() == 0) {
                    isInvalidInput = true;
                    return;
                } else if (newAccountName.equalsIgnoreCase("X")) {
                    isInvalidInput = false;
                    infoToEdit = null;
                    return;
                }

                accountToEdit.setAccountName(newAccountName);

                System.out.println();
                System.out.println(centerText("New Account Name: " + accountToEdit.getAccountName()));
                break;

            case BALANCE:
                System.out.println(centerText("Editing Balance"));
                System.out.println(centerText("Enter X to Go Back"));
                System.out.println();

                System.out.print(centerText("", -5));
                String strNewBalance = input.readLine();

                if (strNewBalance.length() == 0) {
                    isInvalidInput = true;
                    return;
                } else if (strNewBalance.toUpperCase().charAt(0) == 'X') {
                    isInvalidInput = false;
                    infoToEdit = null;
                    return;
                }

                try {
                    double newBalance = Double.parseDouble(strNewBalance);
                    
                    if (newBalance < 0) {
                        isInvalidInput = true;
                        return;
                    }

                    accountToEdit.setBalance(newBalance);
                } catch (NumberFormatException e) {
                    isInvalidInput = true;
                    return;
                }

                System.out.println();
                System.out.println(centerText("New Account Balance: " + accountToEdit.getBalance()));

        }

        isInvalidInput = false;
        accountToEdit = null;
        infoToEdit = null;

        System.out.print("Press ENTER to Go Back to Menu");
        input.readLine();

        data.setActiveView(ViewType.ADMIN_MENU);
    }
}
