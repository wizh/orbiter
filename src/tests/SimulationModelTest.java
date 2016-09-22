import java.io.IOException;

import org.junit.*;
import static org.junit.Assert.*;

/**
* Tests for SimulationModel.
* @author Mathias Fleig Mortensen
*/
public class SimulationModelTest
{
    /**
    * Constructor test.
    * @throws IOException if the relevant data files are not present
    */
    @Test
    public void SimulationModelConstructorTest() throws IOException
    {
        SimulationModel simModel = new SimulationModel();

        String[] celestialNames = new String[]{"Mercury", "Venus", "Earth", "Mars",
                                      "Jupiter", "Saturn", "Uranus",
                                      "Neptune", "Pluto", "Sun"};

        String[] planetNames = new String[]{"Mercury", "Venus", "Earth", "Mars",
                                      "Jupiter", "Saturn", "Uranus",
                                      "Neptune", "Pluto"};

        assertArrayEquals(celestialNames, simModel.getCelestialNames());
        assertArrayEquals(planetNames, simModel.getPlanetNames());
    }

    /**
    * Test for changeSimulation and getSimulation.
    * @throws IOException if the relevant data files are not present
    */
    @Test
    public void changeSimulationTest() throws IOException
    {
        SimulationModel simModel = new SimulationModel();
        Simulation sim = new Simulation();

        simModel.changeSimulation(sim);
        assertEquals(sim, simModel.getSimulation());
    }
}