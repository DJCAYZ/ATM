package views;

import account.Account;

public class ViewData {
    private static ViewData instance;

    private Account activeAccount;
    private ViewType activeView;

    // Makes the class into a singleton class
    private ViewData() {
        activeAccount = null;
        activeView = ViewType.START_MENU;
    }
    public static ViewData getInstance() {
        if (instance == null) {
            instance = new ViewData();
        }

        return instance;
    }

    // Accessors (GETers)
    public Account getActiveAccount() {
        return this.activeAccount;
    }

    public ViewType getActiveView() {
        return this.activeView;
    }

    // Mutators (SETers)
    public void setActiveAccount(Account newActiveAccount) {
        this.activeAccount = newActiveAccount;
    }

    public void setActiveView(ViewType newActiveView) {
        this.activeView = newActiveView;
    }
}
