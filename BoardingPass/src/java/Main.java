import java.io.*;
import java.nio.*;
import java.util.Locale;
import java.util.Scanner;

import Objects.BoardingPass;
import Objects.Person;
import ConsoleUI.PassUI;

public class Main {
    static Scanner scan = new Scanner(System.in);

    private static void MainMenu() {
        boolean run = true;

        while (run) {
            PassUI.ConsoleMenuHeader("Main");
            String input = scan.nextLine().toLowerCase();
            switch (input) {
                case ("person") :
                    PersonMenu();
                    run = false;
                    break;
                case ("flight") :
                    FlightMenu();
                    run = false;
                    break;
                default :
                    System.out.println("Sorry, but that's not a valid option...\nTry again.");
                    break;
            }
        }
    }

    private static void PersonMenu() {
        boolean run = true;
        boolean r = true;

        while (run) {
            PassUI.ConsoleQuestionLine("Person",1);
                String name = scan.nextLine();

            PassUI.ConsoleQuestionLine("Person",2);
                String gender = scan.nextLine();

            PassUI.ConsoleQuestionLine("Person",3);
                r = true;
                while (r) {
                    String input = scan.nextLine();
                    try {
                        int age = Integer.parseInt(input);
                        r = false;
                    } catch (NumberFormatException e) {
                        PassUI.ConsoleErrorOut("Age");
                    }
                }

            PassUI.ConsoleQuestionLine("Person",4);
                String email = scan.nextLine();

            PassUI.ConsoleQuestionLine("Person",5);
                String phone = scan.nextLine();

        }
    }

    private static void FlightMenu() {
        boolean run = true;

        while (run) {
            PassUI.ConsoleQuestionLine("Flight",1);
            String origin = scan.nextLine();
            PassUI.ConsoleQuestionLine("Flight",2);
            String destination = scan.nextLine();
            PassUI.ConsoleQuestionLine("Flight",3);
            String departure= scan.nextLine();
            PassUI.ConsoleQuestionLine("Flight",4);
            String arrival = scan.nextLine();
        }
    }


    public static void main(String[] args) {
        MainMenu();
    }
}
