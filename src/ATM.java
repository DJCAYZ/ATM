import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import account.Account;
import views.View;
import views.ViewAccountBalance;
import views.ViewAccountDeposit;
import views.ViewAccountMenu;
import views.ViewAccountWithdrawal;
import views.ViewAdminAddUser;
import views.ViewAdminEditUserInfo;
import views.ViewAdminEditUserPIN;
import views.ViewAdminMenu;
import views.ViewData;
import views.ViewExiting;
import views.ViewLogin;
import views.ViewLoginFailed;
import views.ViewStartMenu;
import views.ViewType;

public class ATM {
    // private static ArrayList<View> views = new ArrayList<View>(20);
    private static HashMap<ViewType, View> views = new HashMap<ViewType, View>();
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        ViewData data = ViewData.getInstance();
        boolean running = true;

        initAccounts();
        initViews();

        while (running) {
            if (data.getActiveView() == ViewType.EXITING) {
                running = false;
            }
            
            views.get(data.getActiveView()).render();

        }
    }
    
    private static void initAccounts() {
        Account.addAdminAccount("0000-0000-0000", "Administrator", "0000");
        Account.addAccount("0123-4567-8901", "Roel Richard", 5000.00, "1111");
        Account.addAccount("2345-6789-0123", "Dorie Marie", 0.00, "2222");
        Account.addAccount("3456-7890-1234", "Railee Darrel", 10000.00, "3333");
        Account.addAccount("4567-8901-2345", "Railynne Dessirei", 2500.00, "4444");
        Account.addAccount("5678-9012-3456", "Raine Dessirei", 10000.00, "5555");
    }

    private static void initViews() {
        views.put(ViewType.START_MENU, new ViewStartMenu(input));
        views.put(ViewType.LOGIN, new ViewLogin(input));
        views.put(ViewType.LOGIN_FAILED, new ViewLoginFailed(input));

        views.put(ViewType.ACCOUNT_MENU, new ViewAccountMenu(input));
        views.put(ViewType.ACCOUNT_BALANCE, new ViewAccountBalance(input));
        views.put(ViewType.ACCOUNT_WITHDRAWAL, new ViewAccountWithdrawal(input));
        views.put(ViewType.ACCOUNT_DEPOSIT, new ViewAccountDeposit(input));

        views.put(ViewType.ADMIN_MENU, new ViewAdminMenu(input));
        views.put(ViewType.ADMIN_ADD_USER, new ViewAdminAddUser(input));
        views.put(ViewType.ADMIN_EDIT_USER_INFO, new ViewAdminEditUserInfo(input));
        views.put(ViewType.ADMIN_EDIT_USER_PIN, new ViewAdminEditUserPIN(input));

        views.put(ViewType.EXITING, new ViewExiting(input));
    }
}
