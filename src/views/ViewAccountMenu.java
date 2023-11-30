package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewAccountMenu extends View {
    private boolean isInvalidInput;
    public ViewAccountMenu(BufferedReader input) {
        super(input);
        isInvalidInput = false;
    }

    public void display() throws IOException {
        if (isInvalidInput) {
            System.out.println(centerText("Invalid input. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Select Type of Transaction"));
        System.out.println();
        System.out.println(centerText("B -> Balance Inquiry"));
        System.out.println(centerText("W -> Withdrawal", -2));
        System.out.println(centerText("D -> Deposit", -4));
        System.out.println(centerText("C -> Cancel", -4));
        System.out.println();

        System.out.print(centerText("Enter transaction type: ", -1));
        String transactionType = input.readLine();

        if (transactionType.length() == 0) {
            isInvalidInput = true;
            return;
        }

        switch (transactionType.toUpperCase().charAt(0)) {
            case 'B':
                data.setActiveView(ViewType.ACCOUNT_BALANCE);
                break;
            case 'W':
                data.setActiveView(ViewType.ACCOUNT_WITHDRAWAL);
                break;
            case 'D':
                data.setActiveView(ViewType.ACCOUNT_DEPOSIT);
                break;
            case 'C':
                data.setActiveAccount(null);
                data.setActiveView(ViewType.START_MENU);
                break;
            default:
                isInvalidInput = true;
                return;
        }

        isInvalidInput = false;
    }
}
