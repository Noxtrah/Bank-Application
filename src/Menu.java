import java.sql.SQLOutput;
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
        switch(choice){
            case 0:
                System.out.println("Thank you for using my application.");
                System.exit(0);
                break;
            case 1:
                createAnAccount();
            case 2:
                makeADeposit();
            case 3:
                makeAWithdrawal();
            case 4:
                listBalances();
            default:
                System.out.println("Unknown error has occurred.");
        }
    }

    private void listBalances() {
    }

    private void makeAWithdrawal() {
    }

    private void makeADeposit() {
    }

    private void createAnAccount() {
        String firstName, lastName, ssn, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        while(!valid){
            System.out.println("Please enter an account type (checking/savings)");
            accountType = keyboard.nextLine();
            if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")){
                valid = true;
            }
            else{
                System.out.println("Invalid account type selected. Please enter checking or savings.");
            }
        }
        System.out.println("Please enter your first name: ");
        firstName = keyboard.nextLine();
        System.out.println("Please enter your last name: ");
        lastName = keyboard.nextLine();
        System.out.println("Please enter your social security number: ");
        ssn = keyboard.nextLine();
        valid = false;
        while (!valid){
            System.out.println("Please enter initial deposit: ");
            try{
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Deposit must be a number");
            }
            if (accountType.equalsIgnoreCase("checking")){
                if(initialDeposit < 100){
                    System.out.println("Checking accounts require minimum $100 to open.");
                }
                else{
                    valid = true;
                }
            }
            else if (accountType.equalsIgnoreCase("savings")){
                if (initialDeposit < 50){
                    System.out.println("Savings accounts require minimum $50 to open.");
                }
                else {
                    valid = true;
                }
            }
        }
        //We can create an account now
        Account account = new Account();
        if (accountType.equalsIgnoreCase("cheking")){
            account = new Checking(initialDeposit);
        }
        else {
            account = new Savings(initialDeposit);
        }
    }

    private int getInput() {
        int choice = -1;
        do{
        try {
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
}
