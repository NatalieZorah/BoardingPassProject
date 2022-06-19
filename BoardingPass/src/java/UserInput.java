import ConsoleUI.PassUI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.*;


public class UserInput {
    static Scanner scan = new Scanner(System.in);

    private static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    private static boolean isValidInput(String what,String input) throws InvalidInputException {
        if (input.isBlank()) {
            throw new InvalidInputException("Your input is blank, try again.");
        }

        switch (what) {
            case ("Name") :
                // match for only letters and white space
                Pattern name = Pattern.compile("^[a-zA-Z\\\\s]*$");
                Matcher nameMatcher = name.matcher(input.trim());
                if (nameMatcher.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("No special characters or numbers. Try again.");
                }

            case ("Gender") :
                // checks for male, female, enby and non-binary
                if (input.trim().charAt(1) == 'm' || input.trim().charAt(1) == 'f' || input.trim().charAt(1) == 'n' || input.trim().charAt(1) == 'e') {
                    return true;
                } else {
                    throw new InvalidInputException("Unrecognized gender option. Try Again");
                }

            case ("Email") :
                // checks local and domain: letters Az-Zz and 0-9 allowed
                // underscores, dots, and hyphens allowed but...
                // no consecutive dots and no hyphens and dots at beginning and end of domain
                // for local part, maximum of 64 characters allowed
                Pattern email = Pattern.compile("^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@"
                        + "[^-][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$");
                Matcher emailMatcher = email.matcher(input.trim());
                if (emailMatcher.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("Not a valid email. Try again.");
                }

            case ("Phone") :
                // validate phone numbers of format "1234567890"
                Pattern phone1 = Pattern.compile("\\d{10}");
                Matcher phoneMatcher1 = phone1.matcher(input.trim());
                // validating phone number with -, . or spaces
                Pattern phone2 = Pattern.compile("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
                Matcher phoneMatcher2 = phone2.matcher(input.trim());
                if (phoneMatcher1.matches() || phoneMatcher2.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("Not a valid phone number. Try again.");
                }

            case ("Origin") :
                // match for only letters and white space
                Pattern origin = Pattern.compile("^[a-zA-Z\\\\s]*$");
                Matcher originMatcher = origin.matcher(input.trim());
                if (originMatcher.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("Not a valid Origin name. Try again.");
                }

            case ("Destination") :
                // match for only letters and white space
                Pattern destination = Pattern.compile("^[a-zA-Z\\\\s]*$");
                Matcher destinationMatcher = destination.matcher(input.trim());
                if (destinationMatcher.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("Not a valid Destination name. Try again.");
                }

            case ("DepartureTime") :
                // match for only 24 hr time of hh:mm
                Pattern time = Pattern.compile("^(\\\\d\\\\d:\\\\d\\\\d)$");
                Matcher timeMatcher = time.matcher(input.trim());
                if (timeMatcher.matches()) {
                    return true;
                } else {
                    throw new InvalidInputException("Invalid Time Format. Expected hh:mm in 24hr.");
                }

            default :
                return false;
        }
    }

    public static String getName() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Name",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static String getGender() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Gender",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static int getAge() {
        int output = 0;
        boolean run = true;
        while (run) {
            try {
                output = scan.nextInt();
                if (output >= 0 && output <= 130) {
                    run = false;
                } else {
                    PassUI.ConsoleErrorOut("Number out of range.");
                    PassUI.ConsoleErrorOut("Please enter a number between 0-130.");
                    output = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                PassUI.ConsoleErrorOut("Please enter a number between 0-130.");
                scan.next();
            }
        }
        return output;
    }

    public static String getEmail() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Email",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static String getPhone() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Phone",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static String getOrigin() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Origin",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static String getDestination() {
        String output = scan.nextLine();
        try {
            if (isValidInput("Destination",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static String getDepartureTime() {
        String output = scan.nextLine();
        try {
            if (isValidInput("DepartureTime",output)) {
                return output;
            }
        } catch (InvalidInputException e) {
            PassUI.ConsoleErrorOut(e.getMessage());
            output = scan.nextLine();
        }
        return output;
    }

    public static int getFlightLength() {
        int output = 0;
        boolean run = true;
        while (run) {
            try {
                output = scan.nextInt();
                if (output >= 0) {
                    run = false;
                } else {
                    PassUI.ConsoleErrorOut("Please enter a positive number");
                    output = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                PassUI.ConsoleErrorOut("Please enter a number between 0-130.");
                scan.next();
            }
        }
        return output;
    }
}
