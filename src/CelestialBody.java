/**
* Abstract class for the sun and planets to extend.
* @author Mathias Fleig Mortensen
*/
public abstract class CelestialBody
{
    private String name;
    private Vector3D position;
    private Vector3D velocity;
    private Vector3D acceleration;

    /**
    * The main constructor.
    * @param name the name of the planet
    * @param position the position of the planet
    * @param velocity the velocity of the planet
    * @param acceleration the acceleration of the planet
    */
    public CelestialBody(String name, Vector3D position,
                         Vector3D velocity, Vector3D acceleration)
    {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    /**
    * Gets the name.
    * @return the name of the planet.
    */
    public String getName()
    {
        return name;
    }

    /**
    * Gets the position.
    * @return the position of the planet.
    */
    public Vector3D getPosition()
    {
        return position;
    }

    /**
    * Sets the position.
    * @return the position of the planet.
    */
    public void updatePosition(Vector3D position)
    {
        this.position = position;
    }

    /**
    * Gets the velocity.
    * @return the velocity of the planet.
    */
    public Vector3D getVelocity()
    {
        return velocity;
    }

    /**
    * Gets the acceleration.
    * @return the acceleration of the planet.
    */
    public Vector3D getAcceleration()
    {
        return acceleration;
    }
}