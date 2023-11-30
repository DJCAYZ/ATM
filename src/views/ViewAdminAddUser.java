package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

import account.Account;

public class ViewAdminAddUser extends View {
    private Account newAccount;
    private boolean isInvalidInput;

    public ViewAdminAddUser(BufferedReader input) {
        super(input);

        isInvalidInput = false;
        newAccount = new Account();
    }

    public void display() throws IOException {
        if (isInvalidInput && newAccount.getAccountNumber() == null) {
            System.out.println(centerText("Invalid account number. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter the new account's number"));
        System.out.println(centerText("Enter X to Go Back"));
        System.out.println(centerText("Format: ####-####-####"));

        if (newAccount.getAccountNumber() == null) {
            System.out.print(centerText("", -7));
            String newAccountNumber = input.readLine();

            if (newAccountNumber.length() == 0) {
                isInvalidInput = true;
                return;
            } else if (newAccountNumber.toUpperCase().charAt(0) == 'X') {
                isInvalidInput = false;
                newAccount = new Account();
                data.setActiveView(ViewType.ADMIN_MENU);
                return;
            } else if (!Pattern.compile("\\d{4}-\\d{4}-\\d{4}").matcher(newAccountNumber).matches()) {
                isInvalidInput = true;
                return;
            }

            newAccount.setAccountNumber(newAccountNumber);
            isInvalidInput = false;
        } else {
            System.out.println(centerText(newAccount.getAccountNumber()));
        }

        System.out.println();

        if (isInvalidInput && newAccount.getAccountName() == null) {
            System.out.println(centerText("Invalid account name. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter the new account's name"));
        System.out.println(centerText("Enter X to Go Back"));

        if (newAccount.getAccountName() == null) {
            System.out.print(centerText("", -10));
            String newAccountName = input.readLine();

            if (newAccountName.length() == 0) {
                isInvalidInput = true;
                return;
            } else if (newAccountName.equalsIgnoreCase("X")) {
                isInvalidInput = false;
                newAccount.setAccountNumber(null);
                return;
            }

            newAccount.setAccountName(newAccountName);
            isInvalidInput = false;
        } else {
            System.out.println(centerText(newAccount.getAccountName()));
        }
        
        System.out.println();

        if (isInvalidInput && newAccount.getBalance() < 0) {
            System.out.println(centerText("Invalid starting balance. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter the new account's starting balance"));
        if (newAccount.getBalance() < 0) {
            System.out.print(centerText("", -5));
            String strStartingBalance = input.readLine();

            if (strStartingBalance.length() == 0) {
                isInvalidInput = true;
                return;
            } else if (strStartingBalance.toUpperCase().charAt(0) == 'X') {
                isInvalidInput = false;
                newAccount.setAccountName(null);
                return;
            }

            try {
                double startingBalance = Double.parseDouble(strStartingBalance);
                newAccount.setBalance(startingBalance);
            } catch (NumberFormatException e) {
                isInvalidInput = true;
                return;
            }
            isInvalidInput = false;
        } else {
            System.out.println(centerText(String.format("%,.2f", newAccount.getBalance())));
        }

        System.out.println();

        if (isInvalidInput && newAccount.getPinNumber() == null) {
            System.out.println(centerText("Invalid PIN number. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Enter the new account's PIN number"));

        if (newAccount.getPinNumber() == null) {
            System.out.print(centerText("", -2));
            String newAccountPIN = input.readLine();

            if (newAccountPIN.length() == 0) {
                isInvalidInput = true;
                return;
            }  else if (newAccountPIN.toUpperCase().charAt(0) == 'X') {
                isInvalidInput = false;
                newAccount.setBalance(-1);
                return;
            } else if (!Pattern.compile("\\d{4}").matcher(newAccountPIN).matches()) {
                isInvalidInput = true;
                return;
            } else if (Account.getAccountFromPin(newAccountPIN) != null) {
                isInvalidInput = true;
                return;
            }

            newAccount.setPinNumber(newAccountPIN);
            isInvalidInput = false;
        } else {
            System.out.println(centerText(newAccount.getPinNumber()));
        }

        System.out.println();
        isInvalidInput = false;

        System.out.println(centerText("New account " + newAccount.getAccountName() + " created."));
        Account.addAccount(newAccount);
        newAccount = new Account();

        System.out.println();
        System.out.print(centerText("Press ENTER to Go Back"));
        input.readLine();

        data.setActiveView(ViewType.ADMIN_MENU);
    }
}
