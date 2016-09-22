import java.io.IOException;

import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for Simulation.
* @author Mathias Fleig Mortensen
*/
public class SimulationTest
{
    /**
    * Determined by the required accuracy of the floating point compararison.
    * These calculations must be very precise,
    * so the epsilon is lower than usual.
    */
    private static final double EPSILON = 1e-09;

    /**
    * Test for main constructor.
    * @throws IOException if Earth or Mars file is not present
    */
    @Test
    public void SimulationConstructorTest() throws IOException
    {
        String[] planetNames = new String[]{"Earth", "Mars"};
        Simulation sim = new Simulation(1.0, 365, planetNames);

        Planet earth = sim.getPlanets().get(0);
        Vector3D earthPos = new Vector3D(-1.813068419866209E-01,
                                         9.642197733507970E-01,
                                         -6.850809238551276E-05);
        Vector3D earthVel = new Vector3D(-1.718334419397708E-02,
                                         -3.209800047122614E-03,
                                         6.736104268755766E-09);

        Planet mars = sim.getPlanets().get(1);
        Vector3D marsPos = new Vector3D(1.078779946408458E+00,
                                        -8.689695800684623E-01,
                                        -4.471549662355358E-02);
        Vector3D marsVel = new Vector3D(9.295529154226732E-03,
                                        1.210951856543451E-02,
                                        2.553303388130906E-05);

        assertEquals(1.0, sim.getDelta(), EPSILON);
        assertEquals(365, sim.getLifetime());
        assertEquals(2, sim.getNumberOfPlanets());
        assertEquals((new Time()).getDaysElapsed(),
                      sim.getTime().getDaysElapsed(), EPSILON);
        assertEquals("Sun", sim.getSun().getName());
        assertArrayEquals(planetNames, sim.getPlanetNames());

        assertEquals(earth.getName(), "Earth");
        assertEquals(earthPos.getX(), earth.getPosition().getX(), EPSILON);
        assertEquals(earthPos.getY(), earth.getPosition().getY(), EPSILON);
        assertEquals(earthPos.getZ(), earth.getPosition().getZ(), EPSILON);

        assertEquals(earthVel.getX(), earth.getVelocity().getX(), EPSILON);
        assertEquals(earthVel.getY(), earth.getVelocity().getY(), EPSILON);
        assertEquals(earthVel.getZ(), earth.getVelocity().getZ(), EPSILON);

        assertEquals(mars.getName(), "Mars");
        assertEquals(marsPos.getX(), mars.getPosition().getX(), EPSILON);
        assertEquals(marsPos.getY(), mars.getPosition().getY(), EPSILON);
        assertEquals(marsPos.getZ(), mars.getPosition().getZ(), EPSILON);

        assertEquals(marsVel.getX(), mars.getVelocity().getX(), EPSILON);
        assertEquals(marsVel.getY(), mars.getVelocity().getY(), EPSILON);
        assertEquals(marsVel.getZ(), mars.getVelocity().getZ(), EPSILON);
    }

    /**
    * Test for constructor initializing with default values.
    * @throws IOException if the file with the data for the sun is not present
    */
    @Test
    public void SimulationInitConstructorTest() throws IOException
    {
        Simulation sim = new Simulation();

        assertEquals(0.0, sim.getDelta(), EPSILON);
        assertEquals(0, sim.getLifetime());
        assertEquals(0, sim.getNumberOfPlanets());
        assertEquals((new Time()).getDaysElapsed(),
                      sim.getTime().getDaysElapsed(), EPSILON);
        assertEquals("Sun", sim.getSun().getName());
    }

    /**
    * Test for updateSimulation.
    * @throws IOException if
    */
    @Test
    public void updateSimulationTest() throws IOException
    {
        String[] planetNames = new String[]{"Earth"};
        Simulation sim = new Simulation(0.5, 31, planetNames);

        sim.updateSimulation();

        // As calculated by hand.
        assertEquals(-0.19847617809839063,
            sim.getPlanets().get(0).getPosition().getX(), EPSILON);
        assertEquals(0.9609347534986644,
            sim.getPlanets().get(0).getPosition().getY(), EPSILON);
        assertEquals(-6.84992992414772E-5,
            sim.getPlanets().get(0).getPosition().getZ(), EPSILON);

        assertEquals(-0.0171259778273486,
            sim.getPlanets().get(0).getVelocity().getX(), EPSILON);
        assertEquals(-0.003510413905176053,
            sim.getPlanets().get(0).getVelocity().getY(), EPSILON);
        assertEquals(1.4963311964388664E-8,
            sim.getPlanets().get(0).getVelocity().getZ(), EPSILON);

        Simulation sim2 = new Simulation(1.0, 31, planetNames);

        sim2.updateSimulation();

        assertEquals(-0.19849018618059797,
            sim2.getPlanets().get(0).getPosition().getX(), EPSILON);
        assertEquals(0.9610099733036744,
            sim2.getPlanets().get(0).getPosition().getY(), EPSILON);
        assertEquals(-6.850135628124401E-5,
            sim2.getPlanets().get(0).getPosition().getZ(), EPSILON);

        assertEquals(-0.017127311865147682,
            sim2.getPlanets().get(0).getVelocity().getX(), EPSILON);
        assertEquals(-0.003510679267162682,
            sim2.getPlanets().get(0).getVelocity().getY(), EPSILON);
        assertEquals(1.4964263336002974E-8,
            sim2.getPlanets().get(0).getVelocity().getZ(), EPSILON);
    }

    /**
    * Test for getHistory using initial positions.
    * @throws PlanetDoesNotExistException if the simulation does not have
    *         a planet with the specified name
    * @throws IOException if a file corresponding to the planetName
    *         is not present
    */
    @Test
    public void getHistoryTest() throws IOException, PlanetDoesNotExistException
    {
        String[] planetNames = new String[]{"Venus", "Jupiter"};
        Simulation sim = new Simulation(1.0, 32, planetNames);
        sim.getPlanets().get(0).addPositionToHistory
            (DataParser.readPosition("Venus", 0));
        sim.getPlanets().get(1).addPositionToHistory
            (DataParser.readPosition("Jupiter", 0));

        assertEquals("0", sim.getHistory("Venus").get(0));
        assertEquals("0", sim.getHistory("Jupiter").get(0));
    }

    /**
    * Test for calcAcc.
    */
    @Test
    public void calcAccTest() throws IOException
    {
        String[] planetNames = new String[]{"Earth"};
        Simulation sim = new Simulation(1.0, 365, planetNames);
        Vector3D earthPos = new Vector3D(-1.813068419866209E-01,
                                         9.642197733507970E-01,
                                         -6.850809238551276E-05);
        // As calculated by hand.
        Vector3D acc = sim.calcAcc(earthPos);
        assertEquals(5.60323288E-05, acc.getX(), EPSILON);
        assertEquals(-3.0087922E-04, acc.getY(), EPSILON);
        assertEquals(8.22815907E-9, acc.getZ(), EPSILON);

        Vector3D acc2 = sim.calcAcc(new Vector3D(1.0, 1.0, 1.0));
        assertEquals(-5.680630152259867E-05, acc2.getX(), EPSILON);
        assertEquals(-5.687272911672392E-05, acc2.getY(), EPSILON);
        assertEquals(-5.673582669186097E-05, acc2.getZ(), EPSILON);
    }
}