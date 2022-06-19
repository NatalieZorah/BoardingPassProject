import java.io.*;
import java.nio.*;
import java.util.Locale;
import java.util.Scanner;

import Objects.BoardingPass;
import Objects.Person;
import ConsoleUI.PassUI;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean gotPerson = false;
    static boolean gotFlight = false;

    private static void MainMenu() {
        boolean run = true;

        while (run) {
            PassUI.ConsoleMenuHeader("Main");
            String input = UserInput.getMenu().toLowerCase();
            switch (input) {
                case ("person") :
                    PersonMenu();
                    run = false;
                    break;
                case ("flight") :
                    FlightMenu();
                    run = false;
                    break;
            }
        }
    }

    private static void PersonMenu() {
        PassUI.ConsoleQuestionLine("Person",1);
        String name = UserInput.getName();

        PassUI.ConsoleQuestionLine("Person",2);
        String gender = UserInput.getGender();

        PassUI.ConsoleQuestionLine("Person",3);
        int age = UserInput.getAge();

        PassUI.ConsoleQuestionLine("Person",4);
        String email = UserInput.getEmail();

        PassUI.ConsoleQuestionLine("Person",5);
        String phone = UserInput.getPhone();

        gotPerson = true;
        if (!gotFlight) {
            FlightMenu();
        } else {
            EndMenu();
        }
    }

    private static void FlightMenu() {
        PassUI.ConsoleQuestionLine("Flight",1);
        String origin = UserInput.getOrigin();
        PassUI.ConsoleQuestionLine("Flight",2);
        String destination = UserInput.getDestination();
        PassUI.ConsoleQuestionLine("Flight",3);
        String departure= UserInput.getDepartureTime();
        PassUI.ConsoleQuestionLine("Flight",4);
        int flightLenght = UserInput.getFlightLength();

        gotFlight = true;
        if (!gotPerson) {
            PersonMenu();
        } else {
            EndMenu();
        }
    }

    private static void EndMenu() {
        System.exit(0);
    }


    public static void main(String[] args) {
        MainMenu();
    }
}
