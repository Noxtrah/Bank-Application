import java.io.Serializable;

public abstract class Account implements Serializable {
    private double balance = 0;
    private int accountNumber;


    Account(int accountId){
        accountNumber = accountId;
    }

    public abstract AccountType getAccountType();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String toString(){
        return "Account Type: " + getAccountType() + "Account\n" +
                "Account Number: " + this.getAccountNumber() + "\n" +
                "Balance: " + this.getBalance() + "\n";
    }
}
