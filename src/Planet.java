import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

/**
* Represents a planet.
* @author Mathias Fleig Mortensen
*/
public class Planet extends CelestialBody
{
    private List<Vector3D> positionHistory;

    /**
    * Constructor initializing a planet from the file.
    * corresponding to the name given.
    * @param name the name of the planet
    * @throws IOException if the file for the planetname is not present
    */
    public Planet(String name) throws IOException
    {
        super(name, DataParser.readPosition(name, 0),
                    DataParser.readVelocity(name, 0), new Vector3D(0.0, 0.0, 0.0));
        this.positionHistory = new ArrayList<Vector3D>();
    }

    /**
    * Constructor initializing a planet given all of required information.
    * @param name the name of the planet
    * @param position the position of the planet
    * @param velocity the velocity of the planet
    * @param acceleration the acceleration of the planet
    */
    public Planet(String name, Vector3D position, Vector3D velocity,
                  Vector3D acceleration, List<Vector3D> positionHistory)
    {
        super(name, position, velocity, acceleration);
        this.positionHistory = positionHistory;
    }

    /**
    * Gets the history of positions to compare with that of JPL.
    * @return the history of positions to compare with that of JPL
    */
    public List<Vector3D> getPositionHistory()
    {
        return this.positionHistory;
    }

    /**
    * Adds a position to the history of positions to compare with the JPL file.
    */
    public void addPositionToHistory(Vector3D pos)
    {
        this.positionHistory.add(pos);
    }
}