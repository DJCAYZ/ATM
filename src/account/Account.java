package account;

import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<Account>();

    private String accountNumber;
    private String accountName;
    private double balance;
    private String pinNumber;
    private boolean isAdmin;

    public Account() {
        this.accountNumber = null;
        this.accountName = null;
        this.balance = -1;
        this.pinNumber = null;
        this.isAdmin = false;
    }

    public Account(String acctNum, String acctName, double startingBalance, String pinNum) {
        this.accountNumber = acctNum;
        this.accountName = acctName;
        this.balance = startingBalance;
        this.pinNumber = pinNum;

        this.isAdmin = false;
    }

    public void withdrawAmount(double amountToWithdraw) throws AccountWithdrawalException {
        if (amountToWithdraw > this.balance) {
            throw new AccountWithdrawalException("Insufficient funds");
        } else if (amountToWithdraw < 100) {
            throw new AccountWithdrawalException("Amount less than 100");
        } else if ((amountToWithdraw % 100) != 0) {
            throw new AccountWithdrawalException("Invalid amount");
        }

        this.balance -= amountToWithdraw;
    }

    public void depositAmount(double amountToDeposit) throws AccountDepositException {
        if (amountToDeposit < 100) {
            throw new AccountDepositException("Amount less than 100");
        }

        this.balance += amountToDeposit;
    }

    // Static methods
    public static Account addAccount(Account newAccount) {
        accounts.add(newAccount);

        return newAccount;
    }

    public static Account addAccount(String accountNumber, String accountName, double startingBalance, String pinNumber) {
        Account newAccount = new Account(accountNumber, accountName, startingBalance, pinNumber);
        return addAccount(newAccount);
    }

    public static void addAdminAccount(String accountNumber, String accountName, String pinNumber) {
        addAccount(accountNumber, accountNumber, 0.0, pinNumber).setIsAdmin(true);
    }
    
    public static Account getAccountFromPin(String accountPin) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getPinNumber().equals(accountPin)) {
                return accounts.get(i);
            }
        }

        return null;
    }

    // Accessors (GETers)
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getPinNumber() {
        return this.pinNumber;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    // Mutators (SETers)
    public void setAccountNumber(String newAccountNumber) {
        this.accountNumber = newAccountNumber;
    }

    public void setAccountName(String newAccountName) {
        this.accountName = newAccountName;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void setPinNumber(String newPin) {
        this.pinNumber = newPin;
    }

    public void setIsAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
