package views;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewAdminMenu extends View {
    private boolean isInvalidInput;
    public ViewAdminMenu(BufferedReader input) {
        super(input);

        isInvalidInput = false;
    }

    public void display() throws IOException {
        if (isInvalidInput) {
            System.out.println(centerText("Invalid input. Please try again."));
        } else {
            System.out.println();
        }

        System.out.println(centerText("Select Type of Admin Operation"));
        System.out.println();
        System.out.println(centerText("A -> Add User", -6));
        System.out.println(centerText("E -> Edit User Information"));
        System.out.println(centerText("P -> Edit User PIN", -4));
        System.out.println(centerText("C -> Cancel", -7));
        System.out.println();

        System.out.print(centerText("Enter admin operation: ", -1));
        String adminOperation = input.readLine();

        if (adminOperation.length() == 0) {
            isInvalidInput = true;
            return;
        }

        switch (adminOperation.toUpperCase().charAt(0)) {
            case 'A':
                data.setActiveView(ViewType.ADMIN_ADD_USER);
                break;
            case 'E':
                data.setActiveView(ViewType.ADMIN_EDIT_USER_INFO);
                break;
            case 'P':
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