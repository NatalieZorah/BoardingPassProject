package ConsoleUI;

import java.io.PrintWriter;
import Objects.BoardingPass;
import Objects.Person;

public class PassUI {
    private static final String barCharH = "=";
    private static final String barCharV = " || ";
    private static final String gapChar = " ";
    private static final PrintWriter writer = new PrintWriter(System.out,true);

    public static void ConsoleMenuHeader(String header) {
        String output = "";
        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
        output = String.format("%s%s%sGenspark Airlines%s%s%s",output,barCharV,gapChar.repeat(16),gapChar.repeat(16),barCharV,"\n");

        switch (header) {
            case "Main":
                output = String.format("%s%s%sMain Menu%s%s%s", output, barCharV,
                        gapChar.repeat(20),
                        gapChar.repeat(20),
                        barCharV, "\n");
                output = String.format("%s%s%s%s%s", output, barCharV, gapChar.repeat(49), barCharV, "\n");
                output = String.format("%s%s Please choose which information to enter first: %s%s", output, barCharV, barCharV, "\n");
                output = String.format("%s%s%sPerson%sFlight%s%s%s", output, barCharV,
                        gapChar.repeat(12),
                        gapChar.repeat(13),
                        gapChar.repeat(12),
                        barCharV, "\n");
                output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
                break;
            case "Flight":
                output = String.format("%s%s%sFlight Information%s%s%s", output, barCharV,
                        gapChar.repeat(15),
                        gapChar.repeat(16),
                        barCharV, "\n");
                output = String.format("%s%s%s%s", output, barCharV, gapChar.repeat(49), barCharV);
                break;
            case "Person":
                output = String.format("%s%s%sPassenger Information%s%s%s", output, barCharV,
                        gapChar.repeat(14),
                        gapChar.repeat(14),
                        barCharV, "\n");
                output = String.format("%s%s%s%s", output, barCharV, gapChar.repeat(49), barCharV);
                break;
        }

        writer.flush();
        writer.println(output);
    }

    public static void ConsoleQuestionLine(String which,int question) {
        String output = "";
        String str = "";

        switch (which) {
            case ("Person") :
                ConsoleMenuHeader("Person");
                switch (question) {
                    case (1) :
                        str = str + "Please enter the passenger's Full Name"; //name
                        break;
                    case (2) :
                        str = str + "Enter the passenger's Gender"; //gender
                        break;
                    case (3) :
                        str = str + "Enter the passenger's Age"; //age
                        break;
                    case (4) :
                        str = str + "Enter the passenger's Email Address"; //email
                        break;
                    case (5) :
                        str = str + "Enter the passenger's Phone Number"; //phone
                        break;
                }
                break;
            case ("Flight") :
                ConsoleMenuHeader("Flight");
                switch (question) {
                    case (1) :
                        str = str + "Please enter the Flight's Origin Point"; //origin
                        break;
                    case (2) :
                        str = str + "Enter the Flight's Destination Point"; //destination
                        break;
                    case (3) :
                        str = str + "Estimated DEPARTURE time for the Flight"; //arrival time
                        break;
                    case (4) :
                        str = str + "Estimated length of the flight in minutes"; //departure time
                        break;
                }
                break;
        }

        int spacel = (int) Math.floor((49-str.length())/2);
        int spacer = (int) Math.ceil((50-str.length())/2);
        output = String.format("%s%s%s%s%s%s%s", output, barCharV,
                gapChar.repeat(spacel),
                str,
                gapChar.repeat(spacer),
                barCharV, "\n");
        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");

        writer.flush();
        writer.println(output);
    }


    public static void ConsoleErrorOut(String str) {
        String output = "";

        int spacel = (int) Math.floor((49-str.length())/2);
        int spacer = (int) Math.ceil((50-str.length())/2);
        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
        output = String.format("%s%s%s%s%s%s%s", output, barCharV,
                gapChar.repeat(spacel),
                str,
                gapChar.repeat(spacer),
                barCharV, "\n");
        output = String.format("%s{||}%s{||}",output,barCharH.repeat(49));

        writer.flush();
        writer.println(output);
    }

    public static void ConsolePassOut(BoardingPass pass) {
        String output = "";
        String arriveDate = "06/15/2022";

        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
        output = String.format("%s%s%sGenspark Airlines%s%s%s",output,barCharV,gapChar.repeat(16),gapChar.repeat(16),barCharV,"\n");
        output = String.format("%s%sBoarding Pass Number:%s%s%s%s",output,barCharV,
                gapChar.repeat(28-pass.getPassNumber().length()),
                pass.getPassNumber(),
                barCharV,"\n");
        output = String.format("%s%s%s%s%s",output,barCharV,gapChar.repeat(49),barCharV,"\n");
        output = String.format("%s%sDeparture Information%s%s%s",output,barCharV,gapChar.repeat(28),barCharV,"\n");
        output = String.format("%s%sDate: %s%sTime: %s%s%s",output,barCharV,
                pass.getDate(),
                gapChar.repeat(22),
                pass.getDepartureTime(),
                barCharV,"\n");
        output = String.format("%s%sLocation: %s%s%s%s",output,barCharV,
                gapChar.repeat(39-pass.getOrigin().length()),
                pass.getOrigin(),
                barCharV,"\n");
        output = String.format("%s%s%s%s%s",output,barCharV,gapChar.repeat(49),barCharV,"\n");
        output = String.format("%s%sArrival Information%s%s%s",output,barCharV,gapChar.repeat(30),barCharV,"\n");
        output = String.format("%s%sDate: %s%sTime: %s%s%s",output,barCharV,
                arriveDate,
                gapChar.repeat(22),
                pass.getArrivalTime(),
                barCharV,"\n");
        output = String.format("%s%sLocation: %s%s%s%s",output,barCharV,
                gapChar.repeat(39-pass.getDestination().length()),
                pass.getDestination(),
                barCharV,"\n");
        output = String.format("%s{||}%s{||}",output,barCharH.repeat(49));

        writer.flush();
        writer.println(output);
    }

    public static void ConsolePersonOut(Person who) {
        String output = "";

        output = String.format("%s%s%sPassenger Information%s%s%s",output,barCharV,gapChar.repeat(14),gapChar.repeat(14),barCharV,"\n");
        output = String.format("%s%sName: %s%sGender: %s%s%s%s",output,barCharV,
                who.getName(),
                gapChar.repeat(28-who.getName().length()),
                who.getGender(),
                gapChar.repeat(7-who.getGender().length()),
                barCharV,"\n");
        output = String.format("%s%sAge: %s%sPhone: %s%s%s%s",output,barCharV,
                who.getAge(),
                gapChar.repeat(23-String.valueOf(who.getAge()).length()),
                who.getPhone(),
                gapChar.repeat(14-who.getPhone().length()),
                barCharV,"\n");
        output = String.format("%s%sEmail: %s%s%s%s",output,barCharV,
                gapChar.repeat(42-who.getEmail().length()),
                who.getEmail(),
                barCharV,"\n");
        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");

        writer.println(output);
    }

}
