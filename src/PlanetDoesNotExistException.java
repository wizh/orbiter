import java.lang.Throwable;

/**
* Thrown if someone requests a planet from the simulation that does not exist.
* @author Mathias Fleig Mortensen
*/
public class PlanetDoesNotExistException extends Throwable
{
    public PlanetDoesNotExistException(){}
}