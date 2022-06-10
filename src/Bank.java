import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.IllegalFormatException;

public class Bank implements Serializable {
    private DatabaseService database = new DatabaseService();

    int openAccount(String firstName, String lastName, String ssn, AccountType accountType, double balancec){
        return database.AddAccount(firstName,lastName,ssn,accountType,balancec);
    }

    Customer getCustomer(int accountId){
        return database.GetAccount(accountId);
    }

    ArrayList<Customer> getCustomers(){
        return database.GetAllAccounts();
    }

    boolean closeAccount(int accountId){
        return database.DeleteAccount(accountId);
    }

    public static double round(double value, int places){
        if(places < 0){
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void withdraw(int accountId, double amount) throws InsufficientFundsException{
        Customer customer = getCustomer(accountId);
        double transactionFee = getTransactionFee(customer.getAccount().getAccountType());
        if(amount + transactionFee > customer.getAccount().getBalance()){
            System.out.println("You have insufficient funds.");
            return;
        }
        double newBalance = customer.getAccount().getBalance() - (amount + transactionFee);
        database.UpdateAccount(accountId,newBalance);
    }

    public void deposit(int accountId, double amount) throws InvalidAmountException{
        Customer customer = getCustomer(accountId);
        if(amount <= 0){
            throw new InvalidAmountException();
        }
        double interest = checkInterest(customer.getAccount().getBalance(),amount);
        double amountToDeposit = amount + (amount * interest);
        database.UpdateAccount(accountId,customer.getAccount().getBalance() + amountToDeposit);
    }

    public double checkInterest(double balance, double amount){
        double interest = 0;
        if(balance > 10000){
            interest = 0.05;
        }
        else{
            interest = 0.02;
        }
        return interest;
    }

    public double getTransactionFee(AccountType accountType){
        double transactionFee = 0;
        switch (accountType){
            case Savings:
            case Checking:
                transactionFee = 5;
                break;
            case Undefined:
                transactionFee = 0;
                break;
        }
        return transactionFee;
    }
}
