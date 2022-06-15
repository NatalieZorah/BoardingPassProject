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
                break;
            case "Pass":
                output = String.format("%s%s%sFlight Information%s%s%s", output, barCharV,
                        gapChar.repeat(15),
                        gapChar.repeat(16),
                        barCharV, "\n");
                break;
            case "Person":
                output = String.format("%s%s%sPassenger Information%s%s%s", output, barCharV,
                        gapChar.repeat(14),
                        gapChar.repeat(14),
                        barCharV, "\n");
                break;
        }

        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
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
