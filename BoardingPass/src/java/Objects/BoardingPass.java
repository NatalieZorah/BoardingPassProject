package Objects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class BoardingPass {
    private static String PassNumber;
    private final String PassengerName;
    private final LocalDate Date;
    private final String Origin;
    private final String Destination;
    private final String ArrivalTime;
    private final String ArrivalDate;
    private final String DepartureTime;
    private final double TicketPrice;
    private static String originName;
    private static String destinationName;


    //constructor for the boarding pass object
    public BoardingPass(String passengerName, String origin, String destination, String departureTime, double ticketPrice) {
        try {
            PassNumber = generatePassNumber();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        PassengerName = passengerName;
        Date = java.time.LocalDate.now();
        Origin = originName;
        Destination = destinationName;
        DepartureTime = departureTime;
        try {
            ArrivalTime = generateArrivalTime("Time",departureTime,generateDistance(origin,destination));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrivalDate = generateArrivalTime("Date",departureTime,generateDistance(origin,destination));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TicketPrice = ticketPrice;
    }


    public static double generateTicketPrice(int age,String gender,int distance) {
        // the average cost of a plane ticket in US Dollars is roughly
        // $50 + cost per mile. Here we are using kilometers but the
        // principal is the same. The lowest average cost globally
        // per kilometer of flight is roughly 10-13 cents per km
        int seed = 50;
        double output = seed + (distance * 0.12);
            if (age <= 12) {
                output = output * 0.5;
            } else if (age >= 60) {
                output = output * 0.4;
            }

            if (gender.toLowerCase().charAt(0) == 'f') {
                output = output * 0.75;
            }
        return Math.round(output * 100) / 100.0;
    }

    private String generateArrivalTime(String which,String departureTime,int distance) {
        String output = "";
        LocalDate date = java.time.LocalDate.now();
        String depart = date + " " + departureTime;
        // average flight time is 12.5-15.5 km/min
        // add the additional takeoff and landing time of 60 minutes
        int flightTime = 60 + (distance / 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime Depart = LocalDateTime.parse(depart,formatter);
        LocalDateTime Arrive = Depart.plusMinutes(flightTime);
        switch (which) {
            case ("Time") :
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                output += Arrive.format(timeFormat);
                break;
            case ("Date") :
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                output += Arrive.format(dateFormat);
                break;
        }
        return output;
    }

    private String generatePassNumber() throws IOException {
        String fileName = "saved passes/boarding_passes.csv";
        Path path = Paths.get(fileName);
        ArrayList<String> allPassNumbers = new ArrayList<>();
        if (Files.exists(path)) {
            ArrayList<String> allPasses = (ArrayList<String>) Files.readAllLines(path);
            for (String currentPass : allPasses) {
                String[] pass = currentPass.split(",", 3);
                if (!allPassNumbers.contains(pass[0])) {
                    allPassNumbers.add(pass[0]);
                }
            }
        }
        String newPassNumber;
        do {
            newPassNumber = getAlphaNumericString(26);
        }
        while (allPassNumbers.contains(newPassNumber));
        return newPassNumber;
    }

    public static int generateDistance(String origin,String destination) throws IOException {
        int output = 0;
        String fileName = "src/resources/world-airports.csv";
        Path path = Paths.get(fileName);
        ArrayList<String> allAirports = (ArrayList<String>) Files.readAllLines(path);
        double latOrigin = 0;
        double longOrigin = 0;
        double latDestination = 0;
        double longDestination = 0;
        for (String currentAirport:allAirports) {
            if (!currentAirport.contains("id,ident,type")) {
                // 23 commas to separate 3,4,5,,7,8,13,16 are stored values
                String regex = ",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))";
                String[] airport = currentAirport.split(regex, 23);
                Airports Airport = new Airports(airport[1], // airport name
                        airport[2], // city
                        airport[3], // country
                        airport[4], // code
                        Double.parseDouble(airport[6]), // latitude
                        Double.parseDouble(airport[7]) // longitude
                );
                if (Airport.getName().equalsIgnoreCase(origin) ||
                        Airport.getCity().equalsIgnoreCase(origin) ||
                        airport[12].contains(origin.replace(' ','_')) ||
                        Airport.getCity().contains(origin)) {
                    latOrigin = Airport.getLatitude();
                    longOrigin = Airport.getLongitude();
                    originName = String.format("%s: %s (%s)",
                            Airport.getCity(),
                            Airport.getCountry(),
                            Airport.getCode());
                } else if (Airport.getName().equalsIgnoreCase(destination) ||
                        Airport.getCity().equalsIgnoreCase(destination) ||
                        airport[12].contains(destination.replace(' ','_')) ||
                        Airport.getCity().contains(destination)) {
                    latDestination = Airport.getLatitude();
                    longDestination = Airport.getLongitude();
                    destinationName = String.format("%s: %s (%s)",
                            Airport.getCity(),
                            Airport.getCountry(),
                            Airport.getCode());
                }
            }
        }
        output = (int) Math.round(flightDistance(latOrigin,latDestination,longOrigin,longDestination));
        return output;
    }

    public static double flightDistance(double lat1,double lat2, double lon1,double lon2) {
        // We use Math.toRadians() to convert the various longitude
        // and latitude values into their radian values
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double distlon = lon2 - lon1;
        double distlat = lat2 - lat1;
        double a = Math.pow(Math.sin(distlat / 2), 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(distlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers.
        // Use 3956 for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    //getters for all the data of the pass
    public String getPassNumber() {return PassNumber;}

    public String getPassengerName() {return PassengerName;}

    public String getDate() {return String.valueOf(Date);}

    public String getOrigin() {return Origin;}

    public String getDestination() {return Destination;}

    public String getArrivalTime() {return ArrivalTime;}

    public String getArrivalDate() {return ArrivalDate;}

    public String getDepartureTime() {return DepartureTime;}

    public double getTicketPrice() {return TicketPrice;}

    //override of the toString() method for this class
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                PassNumber,
                PassengerName,
                Date,
                Origin,
                Destination,
                DepartureTime,
                ArrivalTime,
                TicketPrice
                );
    }

    public void saveToFile() throws IOException {
        String fileName = "saved passes/boarding_passes.csv";
        Path path = Paths.get(fileName);
        String entry = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                PassNumber,
                PassengerName,
                Date,
                Origin,
                Destination,
                DepartureTime,
                ArrivalTime,
                TicketPrice
        );
        try {
            // Create new file
            // if it does not exist
            File f = new File(fileName);
            if (f.createNewFile()) {
                System.out.println("New File Created : " + f.getName());
            }else {
                FileWriter writer = new FileWriter(fileName, true);
                writer.write("\n" + entry);
                writer.flush();
                writer.close();
                System.out.println("Pass Saved");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }


    // sourced externally from GeeksForGeeks
    static String getAlphaNumericString(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }
}
