public class Customer {
private final String lastName, firstName, ssn;
private final Account account;
    Customer(String firstName, String lastName, String ssn, Account account){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;

    }

    public String toString(){
        return "/nCustomer Information/n" + "\n" +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "SSN: " + ssn + "\n" + account;
    }

    public String basicInfo(){
        return "First name: " + firstName +
                " Last name: " + lastName +
                " SSN: " + ssn +
                " Account Number: " + account.getAccountNumber();
    }

    Account getAccount(){
        return account;
    }
}
