import java.util.Scanner;

public class Menu {
    //Instance variables
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();

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
    }

    private int getInput() {
    }

    private void printMenu() {
    }

    private void printHeader() {
        System.out.println("+--------------------------------+");
        System.out.println("");
        System.out.println("      Welcome to my Bank App      ");
        System.out.println("");
        System.out.println("+--------------------------------+");
    }
}
