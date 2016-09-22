import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for Vector3D.
* @author Mathias Fleig Mortensen
*/
public class Vector3DTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    */
    private static final double EPSILON = 1e-6;

    /**
    * Test for constructor.
    */
    @Test
    public void Vector3DConstructorTest()
    {
        Vector3D vec = new Vector3D(1.0, 2.0, 3.0);
        assertEquals(1.0, vec.getX(), EPSILON);
        assertEquals(2.0, vec.getY(), EPSILON);
        assertEquals(3.0, vec.getZ(), EPSILON);
    }

    /**
    * Test for add.
    */
    @Test
    public void addTest()
    {
        Vector3D vec1 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vec2 = new Vector3D(4.0, 5.0, 6.0);
        Vector3D vec3 = vec1.add(vec2);

        assertEquals(5.0, vec3.getX(), EPSILON);
        assertEquals(7.0, vec3.getY(), EPSILON);
        assertEquals(9.0, vec3.getZ(), EPSILON);
    }

    /**
    * Test for mul.
    */
    @Test
    public void mulTest()
    {
        Vector3D vec1 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vec2 = vec1.mul(5.0);

        assertEquals(5.0, vec2.getX(), EPSILON);
        assertEquals(10.0, vec2.getY(), EPSILON);
        assertEquals(15.0, vec2.getZ(), EPSILON);
    }

    /**
    * Test for length.
    */
    @Test
    public void lengthTest()
    {
        Vector3D vec1 = new Vector3D(2.0, 0.0, 0.0);

        assertEquals(2.0, vec1.length(), EPSILON);
    }
}