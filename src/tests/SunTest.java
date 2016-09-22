import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for Sun.
*/
public class SunTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    */
    public static final double EPSILON = 1e-06;

    /**
    * Test for constructor.
    */
    @Test
    public void SunMainConstructorTest() throws IOException
    {
        String name = "Sun";
        Sun sun = new Sun();
        Vector3D pos = DataParser.readPosition(name, 0);
        Vector3D vel = DataParser.readVelocity(name, 0);
        Vector3D acc = new Vector3D(0.0, 0.0, 0.0);

        assertEquals(name, sun.getName());
        assertEquals(pos.getX(), sun.getPosition().getX(), EPSILON);
        assertEquals(pos.getY(), sun.getPosition().getY(), EPSILON);
        assertEquals(pos.getZ(), sun.getPosition().getZ(), EPSILON);

        assertEquals(vel.getX(), sun.getVelocity().getX(), EPSILON);
        assertEquals(vel.getY(), sun.getVelocity().getY(), EPSILON);
        assertEquals(vel.getZ(), sun.getVelocity().getZ(), EPSILON);

        assertEquals(acc.getX(), sun.getAcceleration().getX(), EPSILON);
        assertEquals(acc.getY(), sun.getAcceleration().getY(), EPSILON);
        assertEquals(acc.getZ(), sun.getAcceleration().getZ(), EPSILON);
    }
}