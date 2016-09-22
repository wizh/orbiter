import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for DataParser.
* @author Mathias Fleig Mortensen
*/
public class DataParserTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    */
    private static final double EPSILON = 1e-06;

    /**
    * Test for readPosition.
    * @throws IOException if data files are not present
    */
    @Test
    public void readPositionTest() throws IOException
    {
        Vector3D earthPosActual = DataParser.readPosition("Earth", 0);
        Vector3D earthPosExpected = new Vector3D(-1.813068419866209E-01,
                                                 9.642197733507970E-01,
                                                 -6.850809238551276E-05);


        Vector3D marsPosActual = DataParser.readPosition("Mars", 0);
        Vector3D marsPosExpected = new Vector3D(1.078779946408458E+00,
                                                -8.689695800684623E-01,
                                                -4.471549662355358E-02);

        assertEquals(earthPosExpected.getX(), earthPosActual.getX(), EPSILON);
        assertEquals(earthPosExpected.getY(), earthPosActual.getY(), EPSILON);
        assertEquals(earthPosExpected.getZ(), earthPosActual.getZ(), EPSILON);

        assertEquals(marsPosExpected.getX(), marsPosActual.getX(), EPSILON);
        assertEquals(marsPosExpected.getY(), marsPosActual.getY(), EPSILON);
        assertEquals(marsPosExpected.getZ(), marsPosActual.getZ(), EPSILON);
    }

    /**
    * Test for readVelocity.
    */
    @Test
    public void readVelocityTest() throws IOException
    {
        Vector3D earthVelActual = DataParser.readVelocity("Earth", 0);
        Vector3D earthVelExpected = new Vector3D(-1.718334419397708E-02,
                                                 -3.209800047122614E-03,
                                                  6.736104268755766E-09);

        Vector3D marsVelActual = DataParser.readVelocity("Mars", 0);
        Vector3D marsVelExpected = new Vector3D(9.295529154226732E-03,
                                                1.210951856543451E-02,
                                                2.553303388130906E-05);

        assertEquals(earthVelExpected.getX(), earthVelActual.getX(), EPSILON);
        assertEquals(earthVelExpected.getY(), earthVelActual.getY(), EPSILON);
        assertEquals(earthVelExpected.getZ(), earthVelActual.getZ(), EPSILON);

        assertEquals(marsVelExpected.getX(), marsVelActual.getX(), EPSILON);
        assertEquals(marsVelExpected.getY(), marsVelActual.getY(), EPSILON);
        assertEquals(marsVelExpected.getZ(), marsVelActual.getZ(), EPSILON);

    }
}