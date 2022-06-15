package Objects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Person {
    private final String Name;
    private final String Email;
    private String Phone;
    private String Gender;
    private int Age;

    //constructor for Objects.Person class
    public Person(String name, String email, String phone, String gender, int age) {
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

    public void saveToFile(String path) throws IOException {
        Path fileName = Path.of(path);
        String entry = String.format("%s,%s,%s,%s,%s",
                Name,
                Email,
                Phone,
                Gender,
                Age
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
