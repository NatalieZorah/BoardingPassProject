package Objects;

public class Airports {
    private final String Name;
    private final String Country;
    private final String City;
    private final String Code;
    private final double Latitude;
    private final double Longitude;

    public Airports(String name, String city, String country, String code,double lat, double aLong) {
        Name = name;
        City = city;
        Country = country;
        Code = code;
        Latitude = lat;
        Longitude = aLong;
    }

    public String getName() {return Name;}

    public double getLatitude() {return Latitude;}

    public double getLongitude() {return Longitude;}


    public String getCountry() {return Country;}

    public String getCity() {return City;}

    public String getCode() {return Code;}


    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s",
                Name,
                City,
                Country,
                Code,
                Latitude,
                Longitude
                );
    }
}
