import java.io.IOException;

/**
* Responsible for running the program.
* @author Mathias Fleig Mortensen
*/
public class Main
{
    /**
    * Instantiates the model, the views and the controller.
    * @throws IOExeption if the data is not present
    * @throws PlanetDoesNotExistException if a position history is requested
    *         from a planet that is not instantiated in a simulation.
    */
    public static void main (String[] args)
    throws IOException, PlanetDoesNotExistException
    {
        SimulationModel theModel = new SimulationModel();
        DataView dataView = new DataView(theModel);
        SimulationView simulationView = new SimulationView(theModel);
        new SimulationController(simulationView, dataView, theModel);
    }
}