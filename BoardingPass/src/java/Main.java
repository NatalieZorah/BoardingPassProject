import Objects.Airports;
import Objects.BoardingPass;
import Objects.Person;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import ConsoleUI.PassUI;

public class Main {
    static boolean gotPerson = false;
    static boolean gotFlight = false;
    private static final HashMap<String,Object> newPerson = new HashMap<>();
    private static final HashMap<String,Object> newFlight = new HashMap<>();

    private static void MainMenu() {
        boolean run = true;

        while (run) {
            PassUI.ConsoleMenuHeader("Main");
            String input = UserInput.getUserInput("Menu").toLowerCase();
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
        String name = UserInput.getUserInput("Name");

        PassUI.ConsoleQuestionLine("Person",2);
        String gender = UserInput.getUserInput("Gender");

        PassUI.ConsoleQuestionLine("Person",3);
        int age = Integer.parseInt(UserInput.getUserInput("Age"));

        PassUI.ConsoleQuestionLine("Person",4);
        String email = UserInput.getUserInput("Email");

        PassUI.ConsoleQuestionLine("Person",5);
        String phone = UserInput.getUserInput("Phone");

        BuildPerson(name,gender,age,email,phone);

        gotPerson = true;
        if (!gotFlight) {
            FlightMenu();
        } else {
            try {
                EndMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void BuildPerson(String name,String gender,int age,String email,String phone) {
        newPerson.put("Name",name);
        newPerson.put("Gender",gender);
        newPerson.put("Age",age);
        newPerson.put("Email",email);
        newPerson.put("Phone",phone);
    }


    private static void FlightMenu() {
        PassUI.ConsoleQuestionLine("Flight",1);
        String origin = UserInput.getUserInput("Origin").replace(',','_');
        PassUI.ConsoleQuestionLine("Flight",2);
        String destination = UserInput.getUserInput("Destination").replace(',','_');
        PassUI.ConsoleQuestionLine("Flight",3);
        String departure = UserInput.getUserInput("DepartureTime");

        BuildFlight(origin,destination,departure);

        gotFlight = true;
        if (!gotPerson) {
            PersonMenu();
        } else {
            try {
                EndMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void BuildFlight(String origin,String destination,String departureTime) {
        newFlight.put("Origin",origin);
        newFlight.put("Destination",destination);
        newFlight.put("DepartureTime",departureTime);
    }


    private static void EndMenu() throws IOException {
        Person passenger = new Person((String) newPerson.get("Name"),
                (String) newPerson.get("Gender"),
                (int) newPerson.get("Age"),
                (String) newPerson.get("Email"),
                (String) newPerson.get("Phone"));
        int distance = BoardingPass.generateDistance((String) newFlight.get("Origin"),
                (String) newFlight.get("Destination"));
        double ticket = BoardingPass.generateTicketPrice(passenger.getAge(),passenger.getGender(),distance);
        BoardingPass pass = new BoardingPass(passenger.getName(),
                (String) newFlight.get("Origin"),
                (String) newFlight.get("Destination"),
                (String) newFlight.get("DepartureTime"),
                ticket);
        PassUI.ConsolePassOut(pass);
        PassUI.ConsolePersonOut(passenger);
        boolean run = true;
        while (run) {
            PassUI.ConsoleMenuHeader("Save");
            String save = UserInput.getUserInput("Y/N").toLowerCase();
            switch (save.charAt(0)) {
                case ('n'):
                    PassUI.ConsoleMenuHeader("Re-Submit");
                    String input = UserInput.getUserInput("Menu").toLowerCase();
                    switch (input) {
                        case ("person"):
                            PersonMenu();
                            break;
                        case ("flight"):
                            FlightMenu();
                            break;
                    }
                    run = false;
                    break;
                case ('y'):
                    passenger.saveToFile();
                    pass.saveToFile();
                    run = false;
                    break;
            }
        }
    }

    private static void RerunProgram() {
        boolean run = true;
        while (run) {
            PassUI.ConsoleMenuHeader("Again");
            String input = UserInput.getUserInput("Y/N").toLowerCase();
            switch (input.charAt(0)) {
                case ('n'):
                    System.exit(0);
                    run = false;
                    break;
                case ('y'):
                    gotFlight = false;
                    gotPerson = false;
                    newPerson.clear();
                    newFlight.clear();
                    MainMenu();
                    run = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        MainMenu();
        RerunProgram();
    }
}
