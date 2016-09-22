import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for Time.
* @author Mathias Fleig Mortensen
*/
public class TimeTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    */
    private static final double EPSILON = 1e-6;

    /**
    * Test for constructor.
    */
    @Test
    public void TimeConstructorTest()
    {
        Time time = new Time();

        assertEquals(0, time.getDaysElapsed(), EPSILON);
        assertEquals("01/01/2013", time.getDateString());
        assertEquals(true, time.shouldAddToHistory());
    }

    /**
    * Test for setNextDay.
    */
    @Test
    public void setNextDayTest()
    {
        Time time = new Time();
        time.setNextDay();

        assertEquals(1, time.getDaysElapsed(), EPSILON);
        assertEquals(false, time.shouldAddToHistory());
        assertEquals("02/01/2013", time.getDateString());
    }
}