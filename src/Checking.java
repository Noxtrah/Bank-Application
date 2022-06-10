public class Checking extends Account{
    private static String accountType = "Cheking";

    Checking(int accountId, double initialDeposit){
        super(accountId);
        this.setBalance(initialDeposit);
    }

    public AccountType getAccountType(){
        return AccountType.Checking;
    }
}
