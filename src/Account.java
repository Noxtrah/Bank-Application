public class Account {
    private double balance = 0;
    private double interest = 0.02;
    private int accountNumber;
    private static int numberofAccounts;

    Account(){
        accountNumber = numberofAccounts++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest * 100;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void withdraw(double amount){
        if(amount + 5 > balance){
            System.out.println("You have insufficient funds.");
            return;
        }
        balance -= amount + 5;
        checkInterest(0); //bunu kontrol et
        System.out.println("You have withdrawn $" + amount + " and incurred of fee of $5");
        System.out.println("You now have a balance of " + balance);
    }

    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("You cannot deposit negative money.");
            return; //bunu kontrol et
        }
        checkInterest(amount);
        amount = amount + amount * interest;
        balance += amount;
        System.out.println("You have deposited $" + amount + " with an interest rate of " + (interest * 100) + "%");
        System.out.println("You now have a balance of $" + balance);
    }

    public void checkInterest(double amount){
        if(balance > 10000){
            interest = 0.05;
        }
        else{
            interest = 0.02;
        }
    }
}
