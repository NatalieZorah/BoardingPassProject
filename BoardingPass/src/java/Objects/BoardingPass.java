package Objects;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class BoardingPass {
    private final String PassNumber;
    private final String Date;
    private final String Origin;
    private final String Destination;
    private final String ArrivalTime;
    private final String DepartureTime;


    //constructor for the boarding pass object
    public BoardingPass(String passNumber, String date, String origin, String destination, String arrivalTime, String departureTime) {
        PassNumber = passNumber;
        Date = date;
        Origin = origin;
        Destination = destination;
        ArrivalTime = arrivalTime;
        DepartureTime = departureTime;
    }

    //getters for all the data of the pass
    public String getPassNumber() {return PassNumber;}

    public String getDate() {return Date;}

    public String getOrigin() {return Origin;}

    public String getDestination() {return Destination;}

    public String getArrivalTime() {return ArrivalTime;}

    public String getDepartureTime() {return DepartureTime;}

    //override of the toString() method for this class
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                PassNumber,
                Date,
                Origin,
                Destination,
                ArrivalTime,
                DepartureTime
                );
    }

    public void saveToFile(String path) throws IOException {
        Path fileName = Path.of(path);
        String entry = String.format("%s,%s,%s,%s,%s,%s",
                PassNumber,
                Date,
                Origin,
                Destination,
                ArrivalTime,
                DepartureTime
        );
        try {
            // Create new file
            // if it does not exist
            File f = new File(path);
            if (f.createNewFile()) {
                System.out.println("New File Created : " + f.getName());
            }else {
                System.out.println("File Already Exists");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
        Files.writeString(fileName,entry);
        System.out.println("Pass Saved");
    }
}
