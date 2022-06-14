package ConsoleUI;

public class PassUI {

    public static void ConsoleOut(String PassNumber,String Date,String Origin,String Destination,String ArrivalTime,String DepartureTime) {
        String output = "";
        String barCharH = "=";
        String barCharV = " || ";
        String gapChar = " ";

        output = String.format("%s{||]%s[||}%s",output,barCharH.repeat(49),"\n");
        output = String.format("%s%s%sGenspark Airlines%s%s%s",output,barCharV,gapChar.repeat(16),gapChar.repeat(16),barCharV,"\n");
        output = String.format("%s%sBoarding Pass Number:%s%s%s%s",output,barCharV,
                gapChar.repeat(28-PassNumber.length()),
                PassNumber,
                barCharV,"\n");
        output = String.format("%s{||]%s[||}%s",output,barCharH.repeat(49),"\n");

        System.out.();
        System.out.println(output);
    }

    public static void main(String[] arg) {
        ConsoleOut("1J778k6s2A4f21844562168432",
                "06/14/2022",
                "Los Angeles (LAX)",
                "Yew York (JFK)",
                "02:07",
                "20:33"
                );
    }
}

//output = String.format("%s%s    %s%s",output,barCharV,barCharV,"\n");