import java.io.IOException;

/**
* Responsible for holding the data that the views are requesting.
* @author Mathias Fleig Mortensen
*/
public class SimulationModel
{
    /**
    * Names of the planets including the Sun.
    */
    private final String[] nameOfCelestials =
    new String[]{"Mercury", "Venus", "Earth", "Mars",
                 "Jupiter", "Saturn", "Uranus",
                 "Neptune", "Pluto", "Sun"};

    /**
    * Names of the planets.
    */
    private final String[] nameOfPlanets =
    new String[]{"Mercury", "Venus", "Earth", "Mars",
                 "Jupiter", "Saturn", "Uranus",
                 "Neptune", "Pluto"};

    private Simulation simulation;

    /**
    * Main constructor taking no arguments.
    * @throws IOException if the relevant data files are not present
    */
    public SimulationModel() throws IOException
    {
        this.simulation = new Simulation();
    }

    /**
    * Gets the list of celestial names.
    * @return the list of celestial names
    */
    public String[] getCelestialNames()
    {
        return this.nameOfCelestials;
    }

    /**
    * Gets the list of planet names.
    * @return the list of planet names
    */
    public String[] getPlanetNames()
    {
        return this.nameOfPlanets;
    }

    /**
    * Changes the simulation to the one specified
    * @param simulation the simulation to change to
    */
    public void changeSimulation(Simulation simulation)
    {
        this.simulation = simulation;
    }

    /**
    * Gets the current simulation.
    * @return the current simulation
    */
    public Simulation getSimulation()
    {
        return simulation;
    }
}