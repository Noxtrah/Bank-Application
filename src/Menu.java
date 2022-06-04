import java.util.Scanner;

public class Menu {
    //Instance variables
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args){

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
                //createAnAccount();
            case 2:
                //makeADeposit();
            case 3:
                //makeAWithdrawal();
            case 4:
                //listBalances();
            default:
                System.out.println("Unknown error has occurred.");
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
