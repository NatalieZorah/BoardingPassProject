package ConsoleUI;

import java.io.PrintWriter;

public class PassUI {

    public static void ConsolePassOut(String PassNumber,String Date,String Origin,String Destination,String ArrivalTime,String DepartureTime) {
        String output = "";
        String barCharH = "=";
        String barCharV = " || ";
        String gapChar = " ";
        String arriveDate = "06/15/2022";
        // Create a PrintWriter instance
        PrintWriter writer = new PrintWriter(System.out,true);

        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");
        output = String.format("%s%s%sGenspark Airlines%s%s%s",output,barCharV,gapChar.repeat(16),gapChar.repeat(16),barCharV,"\n");
        output = String.format("%s%sBoarding Pass Number:%s%s%s%s",output,barCharV,
                gapChar.repeat(28-PassNumber.length()),
                PassNumber,
                barCharV,"\n");
        output = String.format("%s%s%s%s%s",output,barCharV,gapChar.repeat(49),barCharV,"\n");
        output = String.format("%s%sDeparture Information%s%s%s",output,barCharV,gapChar.repeat(28),barCharV,"\n");
        output = String.format("%s%sDate: %s%sTime: %s%s%s",output,barCharV,
                Date,
                gapChar.repeat(22),
                DepartureTime,
                barCharV,"\n");
        output = String.format("%s%sLocation: %s%s%s%s",output,barCharV,
                gapChar.repeat(39-Origin.length()),
                Origin,
                barCharV,"\n");
        output = String.format("%s%s%s%s%s",output,barCharV,gapChar.repeat(49),barCharV,"\n");
        output = String.format("%s%sArrival Information%s%s%s",output,barCharV,gapChar.repeat(30),barCharV,"\n");
        output = String.format("%s%sDate: %s%sTime: %s%s%s",output,barCharV,
                arriveDate,
                gapChar.repeat(22),
                ArrivalTime,
                barCharV,"\n");
        output = String.format("%s%sLocation: %s%s%s%s",output,barCharV,
                gapChar.repeat(39-Destination.length()),
                Destination,
                barCharV,"\n");
        output = String.format("%s{||}%s{||}",output,barCharH.repeat(49));

        writer.flush();
        writer.println(output);
    }

    public static void ConsolePersonOut(String name,String email,String phone,String gender,int age) {
        String output = "";
        String barCharH = "=";
        String barCharV = " || ";
        String gapChar = " ";
        PrintWriter writer = new PrintWriter(System.out,true);

        output = String.format("%s%s%sPassenger Information%s%s%s",output,barCharV,gapChar.repeat(14),gapChar.repeat(14),barCharV,"\n");
        output = String.format("%s%sName: %s%sGender: %s%s%s%s",output,barCharV,
                name,
                gapChar.repeat(28-name.length()),
                gender,
                gapChar.repeat(7-gender.length()),
                barCharV,"\n");
        output = String.format("%s%sAge: %s%sPhone: %s%s%s%s",output,barCharV,
                age,
                gapChar.repeat(23-String.valueOf(age).length()),
                phone,
                gapChar.repeat(14-phone.length()),
                barCharV,"\n");
        output = String.format("%s%sEmail: %s%s%s%s",output,barCharV,
                gapChar.repeat(42-email.length()),
                email,
                barCharV,"\n");
        output = String.format("%s{||}%s{||}%s",output,barCharH.repeat(49),"\n");

        writer.println(output);
    }

}
