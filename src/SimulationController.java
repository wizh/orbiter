import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JSlider;
import javax.swing.Timer;

/**
* Responsible for getting user actions and notifying the model when a new
* simulation has been created. It is also responsible for notifying the view
* that the model has changed whenever it is necessary.
* @author Mathias Fleig Mortensen
*/
public class SimulationController
{
    private SimulationView simulationView;
    private DataView dataView;
    private SimulationModel theModel;
    private SimulationViewDrawer viewDrawer;
    private Timer currentTimer;

    /**
    * Main constructor taking the two views and the model.
    * @param simulationView the view handling the simulation
    * @param dataView the view handling the displaying of data
    * @param theModel the model handling the data and calculations
    */
    public SimulationController(SimulationView simulationView,
                                DataView dataView,
                                SimulationModel theModel)
    {
        this.simulationView = simulationView;
        this.viewDrawer = simulationView.getViewDrawer();
        this.dataView = dataView;
        this.theModel = theModel;
        this.currentTimer = new Timer((int)(10), new TimerListener());
        this.simulationView.addNewSimulationListener
        (new NewSimulationListener());
        this.viewDrawer.addZoomListener(new ZoomListener());
        this.dataView.addPlanetComboListener(new PlanetComboListener());
    }

    /**
    * Handles interaciton with the zoomslider in the simulation view.
    */
    class ZoomListener implements ChangeListener
    {
        /**
        * Called whenever the slider value is changed.
        * Changes the zoom of the simulation to the desired value.
        */
        public void stateChanged(ChangeEvent changeEvent)
        {
            JSlider slider = (JSlider) changeEvent.getSource();
            viewDrawer.changeZoom(slider.getValue());
        }
    }

    /**
    * Handles when the simulation should update.
    */
    class TimerListener implements ActionListener
    {
        /**
        * Called when the timer is triggered which happens every time the
        * simulation should update to the next day.
        */
        public void actionPerformed(ActionEvent e)
        {
            if (theModel.getSimulation().getTime().getDaysElapsed() <=
                theModel.getSimulation().getLifetime() ||
                theModel.getSimulation().getLifetime() == 0) {
                try {
                    theModel.getSimulation().updateSimulation();
                    viewDrawer.paintPlanets();
                    if (theModel.getSimulation().getTime().shouldAddToHistory()) {
                        dataView.updateList();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (PlanetDoesNotExistException ex) {
                    ex.printStackTrace();
                }
            } else
                currentTimer.stop();

            theModel.getSimulation().getTime().setNextDay();
        }
    }

    /**
    * Handles interaction with the planet combo box in the data view.
    */
    class PlanetComboListener implements ActionListener
    {
        /**
        * Triggered whenever a new planet is chosen.
        */
        public void actionPerformed(ActionEvent e) {
            try {
                dataView.updateList();
            } catch (PlanetDoesNotExistException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
    * Handles interaction with the "New Simulation" button.
    */
    class NewSimulationListener implements ActionListener
    {
        /**
        * Triggered whenever the button is pressed.
        */
        public void actionPerformed(ActionEvent e)
        {
            try {
                theModel.changeSimulation
                (new Simulation(0.01,
                                simulationView.getLifetimeFieldValue(),
                                simulationView.getPlanetsToSimulate().
                                toArray(new String[0])));
                dataView.clearList();
                viewDrawer.paintPlanets();

                currentTimer.stop();
                currentTimer = new Timer((int)(1), new TimerListener());
                currentTimer.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}