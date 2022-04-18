import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Logic logic = new Logic();
        Scanner input = new Scanner(System.in);
        String userChoice = "";
        while(!(userChoice.equals("exit"))) {
            System.out.println();
            System.out.println("Welcome to the LVM system! Enter your commands:");
            System.out.println();
            System.out.print("cmd# ");
            userChoice = input.nextLine();
            System.out.println();
            logic.Runner(userChoice);
        }

    }
}