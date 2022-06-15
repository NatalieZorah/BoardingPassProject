

import Objects.BoardingPass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardingPassTest {
    @BeforeAll
    static void setup() {
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
        BoardingPass bp = new BoardingPass("5", "today", "seoul", "busan", "1700", "1400");
        assertEquals(bp.getPassNumber(),"5");
        assertEquals(bp.getDate(),"today");
        assertEquals(bp.getOrigin(),"seoul");
        assertEquals(bp.getDestination(),"busan");
        assertEquals(bp.getArrivalTime(),"1700");
        assertEquals(bp.getDepartureTime(),"1400");
    }

}
