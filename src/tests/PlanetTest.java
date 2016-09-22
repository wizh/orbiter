import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for Planet.
* @author Mathias Fleig Mortensen
*/
public class PlanetTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    */
    public static final double EPSILON = 1e-06;

    /**
    * Test for constructor reading from files.
    * @throws IOException if earth file is not present
    */
    @Test
    public void PlanetFileConstructorTest() throws IOException
    {
        String name = "Earth";
        Planet planet = new Planet(name);
        Vector3D pos = DataParser.readPosition(name, 0);
        Vector3D vel = DataParser.readVelocity(name, 0);

        assertEquals(name, planet.getName());
        assertEquals(pos.getX(), planet.getPosition().getX(), EPSILON);
        assertEquals(pos.getY(), planet.getPosition().getY(), EPSILON);
        assertEquals(pos.getZ(), planet.getPosition().getZ(), EPSILON);

        assertEquals(vel.getX(), planet.getVelocity().getX(), EPSILON);
        assertEquals(vel.getY(), planet.getVelocity().getY(), EPSILON);
        assertEquals(vel.getZ(), planet.getVelocity().getZ(), EPSILON);
    }

    /**
    * Test for constructor with parameters.
    */
    @Test
    public void PlanetParameterConstructorTest()
    {
        String name = "Imaginary Planet";
        Vector3D pos = new Vector3D(5.0, 5.0, 5.0);
        Vector3D vel = new Vector3D(2.0, 4.0, 6.0);
        Vector3D acc = new Vector3D(3.0, -3.5, 0.0);

        Planet planet = new Planet(name, pos, vel, acc, new ArrayList<Vector3D>());

        assertEquals(name, planet.getName());
        assertEquals(pos, planet.getPosition());
        assertEquals(vel, planet.getVelocity());
        assertEquals(acc, planet.getAcceleration());
        assertEquals(new ArrayList<Vector3D>(), planet.getPositionHistory());
    }

    /**
    * Test for addPositionHistory.
    * @throws IOException if earth file is not present
    */
    @Test
    public void PlanetAddPosition() throws IOException
    {
        String name = "Earth";
        Planet planet = new Planet(name);

        List<Vector3D> vecs = new ArrayList<Vector3D>();
        vecs.add(new Vector3D(1.0, 2.0, 3.0));
        vecs.add(new Vector3D(4.0, 5.0, 6.0));

        planet.addPositionToHistory(vecs.get(0));
        planet.addPositionToHistory(vecs.get(1));

        assertEquals(vecs, planet.getPositionHistory());
    }
}