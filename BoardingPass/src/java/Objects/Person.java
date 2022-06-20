package Objects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Person {
    private final String Name;
    private String Gender;
    private int Age;
    private final String Email;
    private String Phone;

    //constructor for Objects.Person class
    public Person(String name, String gender, int age, String email, String phone) {
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Gender = gender;
        this.Age = age;
    }

    //getters and setters for each variable
    public String getName() {return Name;}

    public String getEmail() {return Email;}

    public String getPhone() {return Phone;}
    public void setPhone(String phone) {this.Phone = phone;}

    public String getGender() {return Gender;}
    public void setGender(String gender) {this.Gender = gender;}

    public int getAge() {return Age;}
    public void setAge(int age) {this.Age = age;}

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                Name,
                Email,
                Phone,
                Gender,
                Age
        );
    }

    public void saveToFile() throws IOException {
        String fileName = "saved passes/active_passengers.csv";
        Path path = Paths.get(fileName);
        String entry = String.format("%s,%s,%s,%s,%s",
                Name,
                Email,
                Phone,
                Gender,
                Age
        );
        boolean updateFile = false;
        try {
            // Create new file
            // if it does not exist
            File f = new File(fileName);
            if (f.createNewFile()) {
                System.out.println("New File Created : " + f.getName());
            } else {
                ArrayList<String> allPassengers = (ArrayList<String>) Files.readAllLines(path);
                ArrayList<String> newPassengerList = new ArrayList<>();
                for (String currentPassenger : allPassengers) {
                    String[] pass = currentPassenger.split(",", 5);
                    if (pass[0].equalsIgnoreCase(Name)) {
                        newPassengerList.add(entry);
                        updateFile = true;
                    } else {
                        newPassengerList.add(currentPassenger);
                    }
                }
                if (updateFile) {
                    try {
                        Files.delete(path);
                        if (f.createNewFile()) {
                            System.out.println("File entry updated.");
                        }
                    }
                    catch (Exception e) {
                        System.err.println(e);
                    }
                }
                FileWriter writer = new FileWriter(fileName, true);
                writer.write("\n" + entry);
                writer.flush();
                writer.close();
                System.out.println("Passenger Saved");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
