package Objects;

public class Airports {
    private final String Name;
    private final double Lat;
    private final double Long;
    private final String Continent;
    private final String Country;
    private final String City;
    private final String Code;

    public Airports(String name, double lat, double aLong, String continent, String country, String city, String code) {
        Name = name;
        Lat = lat;
        Long = aLong;
        Continent = continent;
        Country = country;
        City = city;
        Code = code;
    }

    public String getName() {return Name;}

    public double getLat() {return Lat;}

    public double getLong() {return Long;}

    public String getContinent() {return Continent;}

    public String getCountry() {return Country;}

    public String getCity() {return City;}

    public String getCode() {return Code;}
}
