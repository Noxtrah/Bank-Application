import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    //Instance variables
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.runMenu();
    }

    public void runMenu(){
        printHeader();
        while(!exit){
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0 -> {
                System.out.println("Thank you for using my application.");
                System.exit(0);
            }
            case 1 -> {
                try {
                    createAnAccount();
                } catch (InvalidAccountTypeException e) {
                    System.out.println("Account was not created successfully.");
                }
            }
            case 2 -> makeADeposit();
            case 3 -> makeAWithdrawal();
            case 4 -> listBalances();
            default -> System.out.println("Unknown error has occurred.");
        }
    }

    private void listBalances() {
        disaplayHeader("List Account Details");

        int account = selectAccount();
        if(account >= 0) {
            disaplayHeader("Account Details");
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }

    private void makeAWithdrawal() {
        disaplayHeader("Make a Withdrawal");

        int account = selectAccount();
        if(account >= 0) {
            double amount = getDollarAmount("How much do you like to withdraw: ");
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    private double getDollarAmount(String question){
        System.out.println(question);
        double amount = 0;
        try {
            amount = Double.parseDouble(keyboard.nextLine());
        } catch (NumberFormatException e) {
            amount = 0;
        }
        return amount;
    }

    private void makeADeposit() {
        disaplayHeader("Make a Deposit");

        int account = selectAccount();
        if(account >= 0) {
            double amount = getDollarAmount("How much do you like to deposit: ");
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <= 0){
            System.out.println("No customers at your bank.");
            return -1;
        }
        System.out.println("Select an account:");
        for (int i = 0; i < customers.size(); i++){
            System.out.println("\t" + (i + 1) + ") " + customers.get(i).basicInfo());
        }
        int account;
        System.out.println("Please enter your selection: "); //Hangi hesabına işlem yapılacağını seç
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        }
        catch (NumberFormatException e){
            account = -1;
        }
        if(account < 0 || account > customers.size()){
            System.out.println("Invalid account selected");
            account = -1;
        }
        return account;
    }

    private String askQuestion(String question, List<String> answers){
        String response = "";
        Scanner input = new Scanner(System.in);
        boolean choices = ((answers == null) || answers.size() == 0) ? false : true; //if answers is null then choices is false. Otherwise choices is size ofS answers List.
        boolean firstRun = true;
        do {
            if(!firstRun){
                System.out.println("Invalid selection. Please try again.");
            }
            System.out.println(question);
            if(choices){
                System.out.print("(");
                for(int i = 0; i < answers.size() - 1; ++i){
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print("): ");
            }
            response = input.nextLine();
            firstRun = false;
            if(!choices){
                break;
            }
        } while (!answers.contains(response));

        return response;
    }

    private double getDeposit(String accountType){
        double initialDeposit = 0;
        boolean valid = false;
        while (!valid){
            System.out.println("Please enter initial deposit: ");
            try{
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Deposit must be a number");
            }
            if (accountType.equals("checking")){
                if(initialDeposit < 100){
                    System.out.println("Checking accounts require minimum $100 to open.");
                }
                else{
                    valid = true;
                }
            }
            else if (accountType.equals("savings")){
                if (initialDeposit < 50){
                    System.out.println("Savings accounts require minimum $50 to open.");
                }
                else {
                    valid = true;
                }
            }
        }
        return initialDeposit;
    }


    private void createAnAccount() throws InvalidAccountTypeException {
        disaplayHeader("Create an Account");

        //Get account information

        String accountType = askQuestion("Please enter an account type:" , Arrays.asList("checking","savings"));

        String firstName = askQuestion("Please enter your first name: ",null);

        String lastName = askQuestion("Please enter your last name: ",null);

        String ssn = askQuestion("Please enter your social security number: ",null);

        double initialDeposit = getDeposit(accountType);
        //We can create an account now
        Account account = new Account();
        if (accountType.equalsIgnoreCase("checking")){
            account = new Checking(initialDeposit);
        }
        else if(accountType.equalsIgnoreCase("savings")) {
            account = new Savings(initialDeposit);
        }
        else {
            throw new InvalidAccountTypeException();
        }
        Customer customer = new Customer(firstName, lastName, ssn, account);
        bank.addCustomer(customer);
    }

    private int getInput() {
        int choice = -1;
        do{
        try {
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(keyboard.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Invalid selection. Numbers only please.");
        }
        if (choice < 0 || choice > 4){
            System.out.println("Choice outside of range. Please chose again.");
        }
        } while (choice < 0 || choice > 4);
        return choice;
    }

    private void printMenu() {
        disaplayHeader("Please Make a Selection");
        System.out.println("Please make a selection");
        System.out.println("1) Crate a new Account");
        System.out.println("2) Make a deposit");
        System.out.println("3) Make a withdrawal");
        System.out.println("4) List account balances");
        System.out.println("0) Exit");
    }

    private void printHeader() {
        System.out.println("+--------------------------------+");
        System.out.println("|                                |");
        System.out.println("|     Welcome to my Bank App     |");
        System.out.println("|                                |");
        System.out.println("+--------------------------------+");
    }

    private void disaplayHeader(String message){
        System.out.println();
        int width = message.length() + 4;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|  " + message + "  |");
        System.out.println(sb.toString());
    }
}
