

import Objects.BoardingPass;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BoardingPassTest {
    @BeforeAll
    static void setup() {
        //Path airports = Paths.get(BoardingPass.WORLD_AIRPORTS_CSV);

        //log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        //log.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void tearDown() {
        //log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        //log.info("@AfterAll - executed after all test methods.");
    }

    /* initial test, testing constructor, 'getter' methods */
    @Test
    public void getTestBPFields() {
        BoardingPass bp = new BoardingPass("Natalie", "Goroka","London", "14:00",145);
        assertEquals("Natalie",bp.getPassengerName());
        //assertEquals("Goroka",bp.getOrigin());
        //assertEquals("London",bp.getDestination());
        assertEquals("14:00",bp.getDepartureTime());
        assertEquals(145,bp.getTicketPrice());
    }

    /* Ticket price tests
    Ticket Price should be calculated as follows:
    Age < = 12, 50% reduction of ticket price regardless of gender
    Age > = 60, 60% reduction of ticket price regardless of gender
    Females, 25% discount on the ticket price
     */
    @Test
    public void testMaleChildTicketPrice()
    {
        int testDistanceKM = 659;
        double expectedNormalPrice = BigDecimal.valueOf(BoardingPass.USD_BASE_PRICE + BoardingPass.USD_PRICE_PER_KM * testDistanceKM).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double expectedChildPrice = BigDecimal.valueOf(expectedNormalPrice* BoardingPass.CHILD_DISCOUNT).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double actualNormalPrice = BoardingPass.generateTicketPrice(BoardingPass.CHILD_MAX_AGE+1,"male",testDistanceKM);
        double actualChildPrice = BoardingPass.generateTicketPrice(BoardingPass.CHILD_MAX_AGE,"male",testDistanceKM);
        assertEquals(expectedNormalPrice,actualNormalPrice);
        assertEquals(expectedChildPrice,actualChildPrice);
    }

    @Test
    public void testFemaleChildTicketPrice()
    {
        int testDistanceKM = 347;
        double expectedFemalePrice = BigDecimal.valueOf((BoardingPass.USD_BASE_PRICE + BoardingPass.USD_PRICE_PER_KM * testDistanceKM)* BoardingPass.FEMALE_DISCOUNT).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double expectedFemaleChildPrice = BigDecimal.valueOf(expectedFemalePrice* BoardingPass.CHILD_DISCOUNT).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double actualFemalePrice = BoardingPass.generateTicketPrice(BoardingPass.CHILD_MAX_AGE+1,"female",testDistanceKM);
        double actualFemaleChildPrice = BoardingPass.generateTicketPrice(BoardingPass.CHILD_MAX_AGE,"female",testDistanceKM);
        assertEquals(expectedFemalePrice,actualFemalePrice);
        assertEquals(expectedFemaleChildPrice,actualFemaleChildPrice);
    }

    @Test
    public void testSeniorTicketPrice()
    {
        int testDistanceKM = 420;
        double expectedSeniorPrice = (BoardingPass.USD_BASE_PRICE + BoardingPass.USD_PRICE_PER_KM * testDistanceKM)* BoardingPass.SENIOR_DISCOUNT;
        double expectedNormalPrice = BoardingPass.USD_BASE_PRICE + BoardingPass.USD_PRICE_PER_KM * testDistanceKM;
        double actualNormalPrice = BoardingPass.generateTicketPrice(BoardingPass.SENIOR_MIN_AGE-1,"male",testDistanceKM);
        double actualSeniorPrice = BoardingPass.generateTicketPrice(BoardingPass.SENIOR_MIN_AGE,"male",testDistanceKM);
        BigDecimal bdExpectedSeniorPrice = BigDecimal.valueOf(expectedSeniorPrice);
        bdExpectedSeniorPrice = bdExpectedSeniorPrice.setScale(2, RoundingMode.HALF_UP);
        expectedSeniorPrice = bdExpectedSeniorPrice.doubleValue();
        BigDecimal bdEexpectedNormalPrice = BigDecimal.valueOf(expectedNormalPrice);
        bdEexpectedNormalPrice = bdEexpectedNormalPrice.setScale(2, RoundingMode.HALF_UP);
        expectedNormalPrice = bdEexpectedNormalPrice.doubleValue();
        assertEquals(expectedNormalPrice,actualNormalPrice);
        assertEquals(expectedSeniorPrice,actualSeniorPrice);
    }

    @Test
    public void testSeniorFemaleTicketPrice()
    {
        int testDistanceKM = 873;
        double expectedNormalFemalePrice = BigDecimal.valueOf((BoardingPass.USD_BASE_PRICE + BoardingPass.USD_PRICE_PER_KM * testDistanceKM)* BoardingPass.FEMALE_DISCOUNT).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double expectedSeniorFemalePrice = BigDecimal.valueOf(expectedNormalFemalePrice* BoardingPass.SENIOR_DISCOUNT).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double actualNormalFemalePrice = BoardingPass.generateTicketPrice(BoardingPass.SENIOR_MIN_AGE-1,"female",testDistanceKM);
        double actualSeniorFemalePrice = BoardingPass.generateTicketPrice(BoardingPass.SENIOR_MIN_AGE,"female",testDistanceKM);
        assertEquals(expectedNormalFemalePrice,actualNormalFemalePrice);
        assertEquals(expectedSeniorFemalePrice,actualSeniorFemalePrice);
    }

    /* Checking world airports file location */
    @Test
    public void testExistsWorldAirportsCSV() {
        Path path = Paths.get(BoardingPass.WORLD_AIRPORTS_CSV);
        assertTrue(Files.exists(path));

    }

    /* ETA tests */
    @Test
    public void testDistanceLondonToNewYork() throws IOException
    {
        String badDepartTime = "14:50";
        //BoardingPass bp = new BoardingPass("Bob Smith", "John F Kennedy International Airport", "Enghien Moisselles Airfield", badDepartTime,108.73);
        assertEquals(5567,BoardingPass.generateDistance("London","New York"));
    }
}
