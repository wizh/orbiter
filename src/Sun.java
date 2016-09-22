import java.io.IOException;

/**
* Represents the sun.
* @author Mathias Fleig Mortensen
*/
public class Sun extends CelestialBody
{
    /**
    * Constructor always setting acceleration to 0.
    * @throws IOException if the file for the sun is not present
    */
    public Sun() throws IOException
    {
        super("Sun", DataParser.readPosition("Sun", 0),
                     DataParser.readVelocity("Sun", 0),
                     new Vector3D(0.0, 0.0, 0.0));
    }
}