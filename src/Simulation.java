import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
* Represents a simulation.
* @author Mathias Fleig Mortensen
*/
public class Simulation
{
    /**
    * An approximation of the gravitational constant
    * multiplied by the mass of the sun
    */
    private static final double GMSUN = 2.959122082322128E-04;

    /**
    * The length of one astronomical unit in kilometers
    */
    private static final double AU = 149597870.7;
    private final double DELTA;
    private final int LIFETIME;
    private Time time;
    private String[] planetNames;
    private List<Planet> planets;
    private Sun sun;

    /**
    * Main constructor.
    * @param delta the number of times to skip for each step in the simulation
    * @param lifetime the number of times before the simulation ends
    * @param planetNames the names of the planets
    * @throws IOException if there is missing a file for one
    *         of the planets in planetNames
    */
    Simulation(double delta, int lifetime, String[] planetNames)
    throws IOException
    {
        this.DELTA = delta;
        this.LIFETIME = lifetime;
        this.time = new Time();
        this.planetNames = planetNames;
        planets = new ArrayList<Planet>();

        for (String planetName : planetNames)
            planets.add(new Planet(planetName));

        this.sun = new Sun();
    }

    /**
    * Constructor initializing an empty simulation.
    * @throws IOException if the file with the data for the sun is not present.
    */
    Simulation() throws IOException
    {
        this.DELTA = 0;
        this.LIFETIME = 0;
        this.time = new Time();
        this.planetNames = new String[]{};
        this.planets = new ArrayList<Planet>();
        this.sun = new Sun();
    }

    /**
    * Gets the delta value.
    * @return the delta value of the simulation
    */
    public double getDelta()
    {
        return this.DELTA;
    }

    /**
    * Gets the lifetime.
    * @return the lifetime of the simulation
    */
    public int getLifetime()
    {
        return this.LIFETIME;
    }

    /**
    * Gets the time since the beginning of the simulation.
    * @return tthe time since the beginning of the simulation
    */
    public Time getTime()
    {
        return this.time;
    }

    /**
    * Gets the names of the planets.
    * @return the names of the planets
    */
    public String[] getPlanetNames()
    {
        return this.planetNames;
    }

    /**
    * Gets the names of the planets.
    * @return the names of the planets
    */
    public int getNumberOfPlanets()
    {
        return this.planets.size();
    }

    /**
    * Gets the list of planets.
    * @return the list of planets in the simulation
    */
    public List<Planet> getPlanets()
    {
        return this.planets;
    }

    /**
    * Gets the sun.
    * @return the sun of the the simulation
    */
    public Sun getSun()
    {
        return this.sun;
    }

    /**
    * Gets a list of strings representing the distances between a planet and
    * the corresponding JPL data. These distances are in kilometers.
    * @param planetName the name of the planet to get history from
    * @return a list of strings representing the distances between a planet and
    *         the corresponding JPL data
    * @throws PlanetDoesNotExistException if the simulation does not have
    *         a planet with the specified name
    * @throws IOException if a file corresponding to the planetName
    *         is not present
    */
    public List<String> getHistory(String planetName)
    throws PlanetDoesNotExistException, IOException
    {
        Planet planet = null;

        for (Planet p : planets) {
            if (p.getName() == planetName) {
                planet = p;
            }
        }

        if (planet == null)
            throw new PlanetDoesNotExistException();

        List<Vector3D> planetPosList = planet.getPositionHistory();
        List<String> ret = new ArrayList<String>(){};
        for (int i = 0; i < planetPosList.size(); i++) {
            Vector3D nasaPos = DataParser.readPosition(planet.getName(), i);
            Vector3D nasaToPlanet = nasaPos.add(planetPosList.get(i).mul(-1));
            double nasaToPosLength = nasaToPlanet.length();
            ret.add(String.valueOf(nasaToPosLength));
        }

        return ret;
    }

    /**
    * Updates the simulation to the next day
    * with the accuracy depending on delta value.
    * @throws IOException if a planet file is removed during execution
    */
    public void updateSimulation() throws IOException
    {
        if (this.time.shouldAddToHistory()) { //Save to planet history.
            for (Planet planet : planets)
                planet.addPositionToHistory(planet.getPosition());
        }

        int planetIndex;

        for (double i = 0.0; i < 1.0; i += DELTA) {
            Vector3D pos, vel, newPos, newVel, newAcc;
            planetIndex = 0;

            for (Planet planet : planets) {
                pos = planet.getPosition();
                vel = planet.getVelocity();
                newPos = pos.add(vel.mul(DELTA));
                newVel = vel.add(calcAcc(pos).mul(DELTA));
                newAcc = calcAcc(newPos);

                planets.set(planetIndex,
                            new Planet(planet.getName(),
                                       newPos, newVel, newAcc,
                                       planet.getPositionHistory()));
                planetIndex++;
            }

        }
    }

    /**
    * Calculates the acceleration of a planet relative to the sun.
    * @param pos the position vector of the planet
    * @return the acceleration vector of the planet
    * @throws IOException if the file for the sun is not present
    */
    public Vector3D calcAcc(Vector3D pos) throws IOException
    {
        Vector3D sunPos = DataParser.readPosition("Sun", 0);
        Vector3D sunToPos = pos.add(sunPos.mul(-1.0));

        return sunToPos.mul((-(GMSUN/Math.pow(sunToPos.length(), 3))));
    }
}