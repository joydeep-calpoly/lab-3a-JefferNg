import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JUnitTests
{
    MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(LocalTime.of(9, 0),  LocalTime.of(15, 0), 10, 60);
    @Test
    void adultTest()
    {
        assertEquals(2400, calculator.computePrice(LocalTime.of(10, 0), 20));
        assertEquals(2700, calculator.computePrice(LocalTime.of(20, 0), 40));
    }

    @Test
    void childTest()
    {
        assertEquals(2100, calculator.computePrice(LocalTime.of(9, 0), 10));
        assertEquals(2400, calculator.computePrice(LocalTime.of(16, 30), 5));
    }

    @Test
    void seniorTest()
    {
        assertEquals(2000, calculator.computePrice(LocalTime.of(14, 59), 65));
        assertEquals(2300, calculator.computePrice(LocalTime.of(21, 20), 70));
    }

    private void badMatineeStartTime()
    {
        new MovieTicketPriceCalculator(null, LocalTime.of(12,0), 0, 100);
    }

    private void badMatineeEndTime()
    {
        new MovieTicketPriceCalculator(LocalTime.of(12,0), null, 0, 100);
    }

    private void startTimePastEndTime()
    {
        new MovieTicketPriceCalculator(LocalTime.of(12,0), LocalTime.of(5, 0), 0, 100);
    }

    @Test
    void invalidCalc()
    {
        Exception e = assertThrows(NullPointerException.class, this::badMatineeStartTime);
        assertEquals("matinee start time cannot be null", e.getMessage());

        e = assertThrows(NullPointerException.class, this::badMatineeEndTime);
        assertEquals("matinee end time cannot be null", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, this::startTimePastEndTime);
        assertEquals("matinee start time cannot come after end time", e.getMessage());
    }
}
